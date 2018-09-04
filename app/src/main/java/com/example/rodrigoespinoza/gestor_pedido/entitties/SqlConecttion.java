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
    
    final String CREATE_TABLE_USER = "CREATE TABLE user (" +
           "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
           "email VARCHAR (50) NOT NULL, " +
           "pass  VARCHAR (50) NOT NULL, " +
           "fecha DATE NOT NULl)";
    
    final String CREATE_TABLE_PERSON ="CREATE TABLE person(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "name VARCHAR(50) NOT NULL, " +
            "last_name VARCHAR(50) NOT NULL," +
            "sexo VARCHAR(100) NOT NULL, " +
            "location VARCHAR(100) NOT NULL, " +
            "id_user INTEGER NOT NULL," +
            "FOREIGN KEY(id_user) REFERENCES USER(id))";

    
    final String CREATE_TABLE_ORDER = "CREATE TABLE order(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "fecha DATE NOT NULL, " +
            "estado VARCHAR(50) NOT NULL, " +
            "total INTEGER NOT NULL, " +
            "id_person INTEGER NOT NULL, " +
            "id_user INTEGER NOT NULL)";

    String[] create_sentences = {CREATE_TABLE_PRODUCT, CREATE_TABLE_USER,CREATE_TABLE_PERSON};
    public SqlConecttion(Context context, String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(Integer i=0; i<this.create_sentences.length; i++){
            db.execSQL(this.create_sentences[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS person");
      db.execSQL("DROP TABLE IF EXISTS product");

    }
}
