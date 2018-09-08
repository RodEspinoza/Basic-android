package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;

public class Activity_edit_product extends Activity implements View.OnClickListener {

    Product product;
    Button btnUpdateProduct, btnDeleteProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        Intent productIntent =  getIntent();
        Bundle productBundle = productIntent.getExtras();

        if (productBundle != null) {
            this.product = new Product(
                    Integer.parseInt(productBundle.get("id").toString()),
                    productBundle.get("name").toString(),
                    Integer.parseInt(productBundle.get("stock").toString())
            );

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDeleteProduct:
                break;
            case R.id.btnUpdateProduct:
                break;
        }
    }
}
