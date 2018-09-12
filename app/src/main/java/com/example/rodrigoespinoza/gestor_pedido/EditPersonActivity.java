package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.util.ArrayList;
import java.util.List;

public class EditPersonActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    Button btnEdit;
    EditText txtNameEdit, txtLastNameEdit;
    Spinner spLocationEdit;
    RadioGroup rgSexoEdit;
    RadioButton rbMachoEdit, rbHembraEdit;

    Integer idPerson;
    String nombre, apellido, localidad, sexo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        txtNameEdit = (EditText) findViewById(R.id.txtNameEdit);
        txtLastNameEdit = (EditText) findViewById(R.id.txtLastNameEdit);
        rgSexoEdit = (RadioGroup) findViewById(R.id.rgSexoEdit);
        rgSexoEdit.setOnCheckedChangeListener(this);
        rbMachoEdit = (RadioButton) findViewById(R.id.rbMachoEdit);
        rbHembraEdit = (RadioButton) findViewById(R.id.rbHembraEdit);
        spLocationEdit = (Spinner) findViewById(R.id.spLocationEdit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);

        Intent intentMenu = getIntent();
        Bundle bundleMenu = intentMenu.getExtras();

        if (bundleMenu != null){
            idPerson = Integer.parseInt(bundleMenu.get("id").toString());
        }

        getCampos(idPerson);

        nombre = txtNameEdit.getText().toString();
        apellido = txtLastNameEdit.getText().toString();

        actualizarPerson(nombre, apellido, sexo , localidad);
    }

    private void actualizarPerson(String nombre, String apellido, String sexo, String localidad) {
    }

    private void getCampos(Integer idPerson) {
        SqlConecttion conn =  new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] paramBuscarPerson = {idPerson.toString()};
        String[] camposTraerPerson = {"name","last_name","sexo","location"};

        final Cursor cursor = db.query("person", camposTraerPerson, "id = ?", paramBuscarPerson, null, null, null);
        cursor.moveToFirst();

        txtNameEdit.setText(cursor.getString(0));
        txtLastNameEdit.setText(cursor.getString(1));

        if (cursor.getString(2).equals("Masculino")){
            rbMachoEdit.setChecked(true);
        } else {
            rbHembraEdit.setChecked(true);
        }

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

        spLocationEdit.setAdapter(arrayAdapter);

        spLocationEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEditPerfil:
                
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbMachoEdit){
            sexo = "Masculino";
        }

        if (checkedId == R.id.rbHembraEdit){
            sexo = "Femenino";
        }
    }
}
