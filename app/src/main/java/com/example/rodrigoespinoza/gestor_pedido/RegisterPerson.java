package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Person;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;
import com.example.rodrigoespinoza.gestor_pedido.entitties.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegisterPerson extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    EditText txtName, txtLastName;
    RadioGroup rgGroup;
    String sexoSelected;
    Spinner location;
    String localidad;

    User user;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        user = new User();
        person = new Person();

        txtName = (EditText) findViewById(R.id.txtName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        rgGroup = (RadioGroup) findViewById(R.id.rgSexo);
        location = (Spinner) findViewById(R.id.spinnerLocation);
        List list_location = new ArrayList<>();
        list_location.add("Santiago");
        list_location.add("Indepencia");
        list_location.add("Conchali");
        list_location.add("Huechuraba");
        list_location.add("Recoleta");
        list_location.add("Providencia");
        list_location.add("Vitacura");
        list_location.add("Lo Barnechea");
        list_location.add("Las Condes");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list_location);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        location.setAdapter(arrayAdapter);

        location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                localidad = location.getSelectedItem().toString();
                Toast.makeText(RegisterPerson.this, localidad.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

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
