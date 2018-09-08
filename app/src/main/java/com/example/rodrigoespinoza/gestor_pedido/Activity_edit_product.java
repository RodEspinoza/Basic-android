package com.example.rodrigoespinoza.gestor_pedido;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;

public class Activity_edit_product extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        //Product product = (Product) getIntent().getSerializableExtra("Product");
        //Product product = getIntent().getExtras();
       // Toast.makeText(this, product.getName(), Toast.LENGTH_SHORT).show();
    }

}
