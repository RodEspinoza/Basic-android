package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

public class EditPersonActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    Button btnEdit;
    EditText txtNameEdit, txtLastNameEdit;
    Spinner spLocationEdit;
    RadioGroup rgSexoEdit;
    RadioButton rbMachoEdit, rbHembraEdit;

    Integer idPerson;
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

        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);

        Intent intentMenu = getIntent();
        Bundle bundleMenu = intentMenu.getExtras();

        if (bundleMenu != null){
            idPerson = Integer.parseInt(bundleMenu.get("id").toString());
        }

        SqlConecttion conn =  new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();

        String[] paramBuscarPerson = {idPerson.toString()};
        String[] camposTraerPerson = {"name","last_name","sexo"};

        Cursor cursor = db.query("person", camposTraerPerson, "id = ?", paramBuscarPerson, null, null, null);
        cursor.moveToFirst();

        txtNameEdit.setText(cursor.getString(0));
        txtLastNameEdit.setText(cursor.getString(1));
        if (cursor.getString(2).equals("Masculino")){
            rbMachoEdit.setChecked(true);
        } else {
            rbHembraEdit.setChecked(true);
        }


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
