package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOpenProducts;// Solo para probar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnOpenProducts = findViewById(R.id.btnOpenProductsView);
        this.btnOpenProducts.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenProductsView:
                Intent intentProducts = new Intent(this, Activity_products.class);
                startActivity(intentProducts);
                Toast.makeText(
                        this, "Open products activity", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
