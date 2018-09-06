package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOpenProducts;// Solo para probar

    //Variables relacionadas al login del usuario
    EditText txtUser, txtPass;
    Button btnLogin, btnRegistrar;

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

        //Importamos las variables del diseño hasta aqui
        btnRegistrar = (Button) findViewById(R.id.btnRegister);
        btnRegistrar.setOnClickListener(this);
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
            case R.id.btnRegister:

                Intent regisrar = new Intent(this, RegisterUser.class);
                startActivity(regisrar);
                Toast.makeText(this, "New Register", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean autenticaUsuario(String user, String pass) {

        SqlConecttion conn =  new SqlConecttion(this, "bd_user", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        try {
            String[] parametrosBuscar = {user, pass};
            String[] camposTraer = {"id"};

            Cursor cursor = db.query("user", camposTraer, "email = ? AND pass = ?", parametrosBuscar, null, null, null);

            cursor.moveToFirst();

            Toast.makeText(this, cursor.getString(0), Toast.LENGTH_SHORT).show();
            cursor.close();
            conn.close();
            return true;
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            conn.close();
            return false;
        } finally {
            return false;
        }
    }
}
