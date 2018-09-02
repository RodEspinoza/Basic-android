package com.example.rodrigoespinoza.gestor_pedido.entitties;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlConecttion extends SQLiteOpenHelper{
    // STRING TO CREATE TABLES


    String location;

    final String CREATE_TABLE_PRODUCT = "CREATE TABLE product(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  name VARCHAR(100),  stock Integer" +
            ")";
    final String CREATE_TABLE_PERSON ="CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  name VARCHAR(100), last_name VARCHAR(100)," +
            "  sexo VARCHAR(100), location VARCHAR(100)" +
    ")";

    String[] create_sentences = {CREATE_TABLE_PRODUCT, CREATE_TABLE_PERSON};
    public SqlConecttion(Context context, String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS person");
      db.execSQL("DROP TABLE IF EXISTS product");

    }
}
