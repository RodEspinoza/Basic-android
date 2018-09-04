package com.example.rodrigoespinoza.gestor_pedido.entitties;

import java.util.Date;

public class Order {
    Integer id_order;
    Date fecha;
    String state;
    Integer total;
    Person person;
    User user;

    public Order() {
    }

    public Order(Integer id_order, Date fecha, String state, Integer total, Person person, User user) {
        this.id_order = id_order;
        this.fecha = fecha;
        this.state = state;
        this.total = total;
        this.person = person;
        this.user = user;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
