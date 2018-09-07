package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Order;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Person;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

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
    private ArrayList<String> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_porder);
        this.spProducts = findViewById(R.id.spProduct);
        this.total = findViewById(R.id.txTotal);
        this.rStatus = findViewById(R.id.rdStatus);

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
            case R.id.btnOpenAddNewOrder:
               submitOrder();

                break;
        }
    }

    private void submitOrder() {
        Product product = getProduct(this.selected_product);
        this.order.setProduct(product);
        this.order.setTotal(Integer.parseInt(this.total.toString()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        this.order.setFecha(date);
        this.order.setState(status.toString());

    }

    private void getProducts() {
        SqlConecttion conn = new SqlConecttion(
                this, "bd_product", null, 1);
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
        SqlConecttion conn = new SqlConecttion(this, "bd_product", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Product product = new Product();
        try{
            String[] params = {productName};
            String[] fields = {"id"};
            Cursor cursor = db.query("product",fields, "name=?", fields,null,null,null);
            cursor.moveToFirst();
            product.setId(cursor.getInt(0));
            cursor.close();
            conn.close();
        }catch (Exception exc){
            product = null;
        }
        return product;
    }

    private Integer registrarOrden(Order order) {
        SqlConecttion conn = new SqlConecttion(this, "bd_person", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            ContentValues newOrder = new ContentValues();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            newOrder.put("fecha", dateFormat.format(date));
            newOrder.put("product", this.order.getProduct().getId());
            newOrder.put("total", this.order.getTotal());

            Long id = db.insert("product", "id", newOrder);
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            db.close();
            conn.close();
            return Integer.parseInt(id.toString());
        } catch (Exception ex){
            db.close();
            return 0;
        } finally {
            db.close();
            return 0;
        }
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