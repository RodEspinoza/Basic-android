package com.example.rodrigoespinoza.gestor_pedido.entitties;

public class Person {
    String name;
    String last_name;
    String sexo;
    String location;
    User user;

    public Person() {

    }

    public Person(String name, String last_name, String sexo, String location, User user) {
        this.name = name;
        this.last_name = last_name;
        this.sexo = sexo;
        this.location = location;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
