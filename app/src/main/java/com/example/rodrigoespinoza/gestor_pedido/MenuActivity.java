package com.example.rodrigoespinoza.gestor_pedido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    String lblUsuario;
    Button btnAddOrder, btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //lblUsuario = (TextView) findViewById(R.id.txtP);
        btnAddOrder = (Button) findViewById(R.id.btnAddOrde);
        btnEditProfile = (Button) findViewById(R.id.btnEditPerfil);
    }
}
