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
import com.example.rodrigoespinoza.gestor_pedido.entitties.User;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    EditText txtEmail, txtPass, txtRePass;
    Button btnAddNewUser;
    User user; // Variable necesaria para crear un nuevo usuario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //Creamos un nuevo usuario
        user = new User();
        //Pasamos las variables del activity a estas variables
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtRePass = (EditText) findViewById(R.id.txtRePass);
        btnAddNewUser = (Button) findViewById(R.id.btnAddNewUser);
        btnAddNewUser.setOnClickListener(this); // Totalmente escuchando
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddNewUser:

                user.setEmail(txtEmail.getText().toString());
                user.setPass(txtPass.getText().toString());
                registrarUsuario(user);

                break;
        }
    }

    private boolean registrarUsuario(User user) {
        SqlConecttion conn = new SqlConecttion(this, "bd_user", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            String[] parametrosInsertar = {user.getEmail().toString(), user.getPass().toString()};
            String[] camposObtenidos = {"id"};

            Cursor insertar = db.query("user", camposObtenidos, "email = ? AND pass = ?", parametrosInsertar, null, null, null);

            insertar.moveToFirst();
            Toast.makeText(this, insertar.getString(0), Toast.LENGTH_SHORT).show();
            insertar.close();
            conn.close();
            return true;
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            conn.close();
            return false;
        }finally {
            conn.close();
        }
    }
}
