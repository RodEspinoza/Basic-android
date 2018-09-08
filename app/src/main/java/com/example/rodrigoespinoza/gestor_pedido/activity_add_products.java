package com.example.rodrigoespinoza.gestor_pedido;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigoespinoza.gestor_pedido.entitties.Product;
import com.example.rodrigoespinoza.gestor_pedido.entitties.SqlConecttion;

import java.sql.SQLException;

public class activity_add_products extends AppCompatActivity implements View.OnClickListener {
    EditText txProductName, txProductStock;
    Product product;
    Button btnSubmitNewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);
        this.txProductName = findViewById(R.id.txProductName);
        this.txProductStock = findViewById(R.id.txProductStock);
        this.product = new Product();
        this.btnSubmitNewProduct = findViewById(R.id.btnSubmitNewProduct);
        this.btnSubmitNewProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmitNewProduct:
                submitProduct();
                break;
        }
    }

    private void submitProduct()
    {
        this.product.setName(this.txProductName.getText().toString());
        this.product.setStock((Integer.parseInt(this.txProductStock.getText().toString())));
        this.addNewProduct(product);

    }
    private void addNewProduct(Product product){
        if(TextUtils.isEmpty(this.product.getName())){

        }
        SqlConecttion conn = new SqlConecttion(
                this, "bd_gestor_pedidos", null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try{

            ContentValues values = new ContentValues();
            values.put("name", product.getName());
            values.put("stock", product.getStock());
            Long id = db.insert("product", "id", values);
            Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show();
            db.close();

        }catch (SQLiteException exc){
            Toast.makeText(this, "500", Toast.LENGTH_SHORT).show();
            db.close();
        }
     }




    private Boolean findProduct(Product product){
        SqlConecttion conn = new SqlConecttion(
                this, "bd_gestor_pedidos", null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        try{
            String[] params = {product.getName()};
            String[] fields = {"id"};
            Cursor cursor =db.query(
                    "product",
                    fields, "name=?",
                    params, null,
                    null, null);
            cursor.moveToFirst();
            cursor.close();
            conn.close();
            Toast.makeText(this, cursor.getString(0),Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (Exception exc){
            Toast.makeText(this, exc.toString(), Toast.LENGTH_SHORT).show();
            conn.close();
            return false;
        }


    }
}
