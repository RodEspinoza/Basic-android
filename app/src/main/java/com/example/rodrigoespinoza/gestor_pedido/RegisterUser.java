package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    EditText txtEmail, txtPass, txtRePass;
    Button btnAddNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

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

                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String rePass = txtRePass.getText().toString();

                if (pass.equals(rePass)){
                    Intent intentNewPerson = new Intent(this, RegisterPerson.class);
                    intentNewPerson.putExtra("email", email);
                    intentNewPerson.putExtra("pass", pass);
                    startActivity(intentNewPerson);
                } else {
                  Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
