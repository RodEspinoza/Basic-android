package com.example.rodrigoespinoza.gestor_pedido;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.util.ArrayList;

public class add_porder extends Activity implements View.OnClickListener {
    Spinner spProducts;
    private ArrayList<String> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_porder);
        this.spProducts = findViewById(R.id.spProduct);
        getProducts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, productList);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        this.spProducts.setAdapter(arrayAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenAddNewOrder:
                break;
        }
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
}