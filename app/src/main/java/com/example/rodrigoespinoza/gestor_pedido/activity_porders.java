package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Order;


public class activity_porders extends Activity implements OnClickListener {
    Button btnOpenProductsView;
    Spinner spProducts;
    Order order;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porders);
        this.btnOpenProductsView = findViewById(R.id.btnOpenAddProduct);
        this.btnOpenProductsView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenAddNewOrder:
                Intent intent = new Intent(this, add_porder.class);
                startActivity(intent);
                break;

        }
    }
}
