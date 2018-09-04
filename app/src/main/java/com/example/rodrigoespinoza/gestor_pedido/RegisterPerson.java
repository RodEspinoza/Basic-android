package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;
import com.example.rodrigoespinoza.gestor_pedido.entitties.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);
    }


    private boolean registrarUsuario(User user) {
        SqlConecttion conn = new SqlConecttion(this, "bd_user", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            ContentValues newUsuario = new ContentValues();
            newUsuario.put("email", user.getEmail());
            newUsuario.put("pass", user.getPass());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            newUsuario.put("fecha", dateFormat.format(date));

            Long id = db.insert("user", "id",newUsuario);
            Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            db.close();
            //En esta seccion debo redireccionar a crear persona

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
