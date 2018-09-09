package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Order;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Person;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;
import com.example.rodrigoespinoza.gestor_pedido.entitties.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Activity_add_order extends Activity implements View.OnClickListener , RadioGroup.OnCheckedChangeListener{
    Spinner spProducts;
    String selected_product;
    Order order;
    RadioGroup rStatus;
    TextView total;
    Boolean status = false;
    Integer person_id;
    User user;
    Button btnSubmitNewOrder;
    private ArrayList<String> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_porder);
        this.spProducts = findViewById(R.id.spProduct);
        this.total = findViewById(R.id.txTotal);
        this.rStatus = findViewById(R.id.rdStatus);
        this.btnSubmitNewOrder = findViewById(R.id.btnSubmitNewOrder);
        this.btnSubmitNewOrder.setOnClickListener(this);
        Intent userMenuIntent =  getIntent();
        Bundle bundleMain = userMenuIntent.getExtras();

        if (bundleMain != null) {
            this.person_id = Integer.parseInt(bundleMain.get("id").toString());
        }

        getProducts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productList);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        this.spProducts.setAdapter(arrayAdapter);
        this.spProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_product = spProducts.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmitNewOrder:
               submitOrder();
               break;
        }
    }

    private void submitOrder() {
        Product product = getProduct(this.selected_product);
        if(product.getId() == null)
        {
            toIntentMenu();
        }
        Person person = new Person();
        person.setId(this.person_id);
        this.order = new Order();
        this.order.setPerson(person);
        this.order.setProduct(product);

        this.order.setTotal(Integer.parseInt(this.total.getText().toString()));
        Date date = new Date();
        this.order.setFecha(date);
        this.order.setState(status.toString());
        if(saveOrder(this.order)>0){
            Toast.makeText(this, "200", Toast.LENGTH_SHORT).show();
        }
    }

    private void getProducts() {
        SqlConecttion conn = new SqlConecttion(
                this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Product product;
        this.productList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM product", null);
        while(cursor.moveToNext()){
            product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setStock(cursor.getInt(2));

            this.productList.add(product.getName().toString());
        }
    }

    public Product getProduct(String productName) {
        SqlConecttion conn = new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Product product = new Product();
        try{
            String[] params = {productName};
            String[] fields = {"id"};
            Cursor cursor = db.query("product",fields, "name=?", params,null,null,null);
            cursor.moveToFirst();
            product.setId(cursor.getInt(0));
            cursor.close();
            conn.close();
        }catch (Exception exc){

            product = null;
        }
        return product;
    }

    private Integer saveOrder(Order order) {
        SqlConecttion conn = new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            ContentValues newOrder = new ContentValues();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            newOrder.put("fecha", dateFormat.format(date));
            newOrder.put("id_product", order.getProduct().getId());
            newOrder.put("id_person", order.getPerson().getId());
            newOrder.put("total", order.getTotal());
            newOrder.put("estado", order.getState());
            Long id = db.insert("pedido", "id", newOrder);

            db.close();
            conn.close();

            return Integer.parseInt(id.toString());
        } catch (Exception ex) {
            db.close();
            return 0;
        }
    }

    private void toIntentMenu(){
        Intent addOrderIntent = new Intent(this, MenuActivity.class);
        addOrderIntent.putExtra("id", this.person_id);
        Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
        startActivity(addOrderIntent);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbFalse:
                this.status = false;
                break;
            case R.id.rbTrue:
                this.status = true;
                break;
        }
    }
}