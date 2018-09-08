package com.example.rodrigoespinoza.gestor_pedido;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAddOrder, btnEditProfile;
    Integer idUser;
    String namePerson;
    Integer idPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAddOrder = (Button) findViewById(R.id.btnAddOrde);
        btnEditProfile = (Button) findViewById(R.id.btnEditPerfil);

        Intent intentMain =  getIntent();
        Bundle bundleMain = intentMain.getExtras();

        if (bundleMain != null) {
            idUser = Integer.parseInt(bundleMain.get("id").toString());
        }

        //Toast.makeText(this, idUser.toString(), Toast.LENGTH_SHORT).show();

        btnAddOrder.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);

        SqlConecttion conn = new SqlConecttion(this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] paramBuscarPerson = {idUser.toString()};
        String[] camposTraerPerson = {"name","id"};

       Cursor cursor = db.query("person", camposTraerPerson, "id_user = ?", paramBuscarPerson, null, null, null);
       cursor.moveToFirst();
       namePerson = cursor.getString(cursor.getColumnIndex("name"));
       idPerson = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddOrde:
                Intent addOrderIntent = new Intent(this, Activity_add_order.class);
                addOrderIntent.putExtra("id", this.idPerson);
                startActivity(addOrderIntent);
                break;
            case R.id.btnEditPerfil:
                break;
        }
    }
}
