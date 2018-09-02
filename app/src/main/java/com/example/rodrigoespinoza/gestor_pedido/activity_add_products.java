package com.example.rodrigoespinoza.gestor_pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class activity_add_products extends AppCompatActivity implements View.OnClickListener {
    EditText txProductName, txProductStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        this.txProductName = findViewById(R.id.txProductName);
        this.txProductStock = findViewById(R.id.txProductStock);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmitNewProduct:
                break;
        }
    }
}
