package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.rodrigoespinoza.gestor_pedido.Fragmentos.LoginFragment;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;
import com.example.rodrigoespinoza.gestor_pedido.Fragmentos.RegistroFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        RegistroFragment.OnFragmentInteractionListener, LoginFragment.OnFragmentInteractionListener
{
    Button btnOpenOrderView;// Solo para probar

    //Variables relacionadas al login del usuario
    EditText txtUser, txtPass;
    Button btnLogin, btnRegistrar;
    RegistroFragment registroFragment;
    LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instancionamos las variables creadas
        //txtUser = (EditText) findViewById(R.id.txtUser);
        //txtPass = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        //Importamos las variables del diseño hasta aqui
        btnRegistrar = (Button) findViewById(R.id.btnRegister);
        btnRegistrar.setOnClickListener(this);
        this.registroFragment = new RegistroFragment();
        this.loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(
                R.id.contenedorFragment, this.loginFragment).commit();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.btnLogin:
                fragmentTransaction.replace(
                        R.id.contenedorFragment, this.loginFragment).commit();
                // auth
                break;
            case R.id.btnRegister:
                fragmentTransaction.replace(
                        R.id.contenedorFragment, this.registroFragment).commit();
                break;
        }
    }

    private Integer autenticaUsuario(String user, String pass) {

        SqlConecttion conn =  new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        try {
            String[] parametrosBuscar = {user, pass};
            String[] camposTraer = {"id"};

            Cursor cursor = db.query("user", camposTraer, "email = ? AND pass = ?", parametrosBuscar, null, null, null);

            cursor.moveToFirst();
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            //Toast.makeText(this, cursor.getString(0), Toast.LENGTH_SHORT).show();
            cursor.close();
            conn.close();
            return id;
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            conn.close();
            return 0;
        } finally {
            conn.close();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
