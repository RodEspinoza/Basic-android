package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Button btnRegister;

    // variables correspodientes a usuario
    User user;
    String email, pass;

    //variables correspodientes a personas
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
        btnRegister = (Button) findViewById(R.id.btnRegisterPerson);
        btnRegister.setOnClickListener(this);
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

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                localidad = location.getSelectedItem().toString();
                Toast.makeText(RegisterPerson.this, localidad.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.rbMan){
            sexoSelected = "Masculino";
        }
        if (checkedId == R.id.rbWoman){
            sexoSelected = "Femenino";
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnRegisterPerson:
                Intent intentBackMain = new Intent(this, MainActivity.class);
                Intent intentUser = getIntent();
                Bundle bundleUser = intentUser.getExtras();

                if(bundleUser != null){
                    email = bundleUser.get("email").toString();
                    pass = bundleUser.get("pass").toString();
                }
                // Agregamos Usuario
                user.setEmail(email);
                user.setPass(pass);

                // Agregamos Person
                person.setName(txtName.getText().toString());
                person.setLast_name(txtLastName.getText().toString());
                person.setSexo(sexoSelected);
                person.setLocation(localidad);
                person.setId_user(registrarUsuario(user));

                if (registrarPersona(person) != 0) {
                    Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
                    startActivity(intentBackMain);
                } else {
                    Toast.makeText(this, "Ha ocurrido un problema", Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    private Integer registrarPersona(Person person) {
        SqlConecttion conn = new SqlConecttion(this, "bd_person", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try{
            ContentValues newPerson = new ContentValues();
            newPerson.put("name", person.getName());
            newPerson.put("last_name", person.getLast_name());
            newPerson.put("sexo", person.getSexo());
            newPerson.put("location", person.getLocation());
            newPerson.put("id_user", person.getId_user());

            Long id = db.insert("person", "id", newPerson);
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            db.close();
            conn.close();
            return Integer.parseInt(id.toString());
        } catch (Exception ex){
            conn.close();
            return 0;
        } finally {
            conn.close();
        }
    }


    private Integer registrarUsuario(User user) {
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
            //Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            db.close();
            //En esta seccion debo redireccionar a crear persona

            conn.close();
            return Integer.parseInt(id.toString());
        }catch (Exception ex){
            //Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            conn.close();
            return 0;
        }finally {
            conn.close();
        }
    }

}
