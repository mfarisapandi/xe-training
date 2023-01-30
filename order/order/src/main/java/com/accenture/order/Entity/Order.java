package com.accenture.order.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Data
public class Order {

    @Id
    private int orderID;

    public Order() {
    }

    public Order(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
