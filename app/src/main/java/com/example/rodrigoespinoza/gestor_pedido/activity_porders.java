package com.example.rodrigoespinoza.gestor_pedido;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class activity_porders extends Activity implements OnClickListener {
    Button btnOpenProductsView;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porders);
        //this.btnOpenProductsView = findViewById(R.id.btnOpenProductsView)
        
    }


    @Override
    public void onClick(View v) {

    }
}
