package com.accenture.order.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "order")
@Data
public class Order {

    @Id
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "order_date")
    private LocalDate orderDate;

    public Order() {
    }

    public Order(int orderID, LocalDate orderDate) {
        this.orderID = orderID;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
