package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Activity_products extends AppCompatActivity implements View.OnClickListener{

    Button openAddProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        this.openAddProduct = findViewById(R.id.btnOpenAddProduct);
        this.openAddProduct.setOnClickListener(this);
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
}
