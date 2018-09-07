package com.example.rodrigoespinoza.gestor_pedido;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Order;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class add_porder extends Activity implements View.OnClickListener {
    Spinner spProducts;
    private ArrayList<String> productList;
    String selected_product;
    Order order;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_porder);
        this.spProducts = findViewById(R.id.spProduct);
        this.total = findViewById(R.id.txTotal);
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


}