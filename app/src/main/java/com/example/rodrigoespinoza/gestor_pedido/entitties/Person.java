package com.example.rodrigoespinoza.gestor_pedido.entitties;

public class Person {
    String name;
    String last_name;
    String sexo;
    String location;
    Integer id;

    public Person(Integer id, String name, String last_name, String sexo, String location) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.sexo = sexo;
        this.location = location;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}
