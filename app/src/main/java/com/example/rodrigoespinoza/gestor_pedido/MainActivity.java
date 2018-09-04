package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOpenProducts;// Solo para probar

    //Variables relacionadas al login del usuario
    EditText txtUser, txtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnOpenProducts = findViewById(R.id.btnOpenProductsView);
        this.btnOpenProducts.setOnClickListener(this);

        //Instancionamos las variables creadas
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPass = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this); // Constantemente escuchando

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOpenProductsView:

                Intent intent = new Intent(this, Activity_products.class);
                startActivity(intent);
                Toast.makeText(
                        this, "Open products activity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLogin:

                String user = txtUser.getText().toString();
                String pass = txtPass.getText().toString();

                autenticaUsuario(user, pass);

                break;
        }
    }

    private boolean autenticaUsuario(String user, String pass) {

        return true;

    }
}
