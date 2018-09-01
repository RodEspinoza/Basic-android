package com.example.rodrigoespinoza.gestor_pedido.entitties;

import java.util.Date;

public class Order {
    Product product;
    String state;
    Integer total;
    Date order_date;

    public Order(Product product, String state, Integer total, Date order_date) {
        this.product = product;
        this.state = state;
        this.total = total;
        this.order_date = order_date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
