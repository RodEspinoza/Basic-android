package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.util.ArrayList;

public class Activity_products extends AppCompatActivity implements View.OnClickListener{
    SqlConecttion conn;
    Button openAddProduct;
    ArrayList<Product> productList;
    ArrayList<String> detailList;
    ListView listViewProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.openAddProduct = findViewById(R.id.btnOpenAddProduct);
        this.openAddProduct.setOnClickListener(this);
        this.listViewProducts = findViewById(R.id.listProduct);
        getProducts();
        ArrayAdapter arrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_expandable_list_item_1, detailList);

        this.listViewProducts.setAdapter(arrayAdapter);
        this.listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String info ="Stock " + productList.get(position).getStock();
                Toast.makeText(Activity_products.this, info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenAddProduct:
                Intent intent = new Intent(this, activity_add_products.class);
                startActivity(intent);
                break;
        }
    }
    private void getProducts(){
        conn = new SqlConecttion(this, "bd_product", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        Product product;
        this.productList = new ArrayList<Product>();
        Cursor cursor = db.rawQuery("SELECT * FROM product", null);
        while(cursor.moveToNext()){
            product = new Product();
            product.setId(cursor.getInt(0));
            product.setName(cursor.getString(1));
            product.setStock(cursor.getInt(2));

            this.productList.add(product);
        }
        setDataToList();
    }
    private void setDataToList()
    {
        this.detailList =  new ArrayList<String>();
        for(int i=0; i < this.productList.size();i++){
            this.detailList.add(
                    this.productList.get(i).getId().toString() +
                            " :" + this.productList.get(i).getName());
        }
    }
}
