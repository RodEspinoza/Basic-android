package com.example.rodrigoespinoza.gestor_pedido;

import android.os.Bundle;
import android.app.Activity;

Button btnOpenProductsView;
public class activity_porders extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) implements View.OnClickListener {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porders);
        //this.btnOpenProductsView = findViewById(R.id.btnOpenProductsView)
        
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
        }
    }
    

}
