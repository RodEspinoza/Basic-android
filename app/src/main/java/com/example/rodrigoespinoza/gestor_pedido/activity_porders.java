package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Order;
import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.util.ArrayList;


public class activity_porders extends Activity implements OnClickListener {
    Button btnOpenAddNewOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porders);
        this.btnOpenAddNewOrder = findViewById(R.id.btnOpenAddNewOrder);
        this.btnOpenAddNewOrder.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenAddNewOrder:
                Intent addOrderIntent = new Intent(this, add_porder.class);
                startActivity(addOrderIntent);
                break;
        }

    }
}
