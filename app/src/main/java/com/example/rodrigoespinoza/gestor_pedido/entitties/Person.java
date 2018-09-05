package com.example.rodrigoespinoza.gestor_pedido.entitties;

public class Person {
    String name;
    String last_name;
    String sexo;
    String location;
    Integer id_user;

    public Person() {

    }

    public Person(String name, String last_name, String sexo, String location, Integer id_user) {
        this.name = name;
        this.last_name = last_name;
        this.sexo = sexo;
        this.location = location;
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
}
