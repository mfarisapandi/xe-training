package com.accenture.order.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "wizard_id")
    private int wizardID;
    @Column(name = "wizard_name")
    private String wizardName;
    @Column(name = "wizard_age")
    private int wizardAge;
    @Column(name = "joined_date")
    private LocalDate joinedDate;
    @Column(name = "is_active")
    private String isActive;
    @Column(name = "magic_wand_id")
    private int magicWandID;
    @Column(name = "magic_wand_name")
    private String magicWandName;
    @Column(name = "magic_wand_desc")
    private String magicWandDesc;
    @Column(name = "age_limit")
    private int ageLimit;
    @Column(name = "stock")
    private int stock;

    public Order() {
    }

    public Order(int orderID, LocalDate orderDate,
                 int wizardID, String wizardName,
                 int wizardAge, LocalDate joinedDate,
                 String isActive, int magicWandID,
                 String magicWandName, String magicWandDesc,
                 int ageLimit, int stock) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.wizardID = wizardID;
        this.wizardName = wizardName;
        this.wizardAge = wizardAge;
        this.joinedDate = joinedDate;
        this.isActive = isActive;
        this.magicWandID = magicWandID;
        this.magicWandName = magicWandName;
        this.magicWandDesc = magicWandDesc;
        this.ageLimit = ageLimit;
        this.stock = stock;
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

    public int getWizardID() {
        return wizardID;
    }

    public void setWizardID(int wizardID) {
        this.wizardID = wizardID;
    }

    public String getWizardName() {
        return wizardName;
    }

    public void setWizardName(String wizardName) {
        this.wizardName = wizardName;
    }

    public int getWizardAge() {
        return wizardAge;
    }

    public void setWizardAge(int wizardAge) {
        this.wizardAge = wizardAge;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public int getMagicWandID() {
        return magicWandID;
    }

    public void setMagicWandID(int magicWandID) {
        this.magicWandID = magicWandID;
    }

    public String getMagicWandName() {
        return magicWandName;
    }

    public void setMagicWandName(String magicWandName) {
        this.magicWandName = magicWandName;
    }

    public String getMagicWandDesc() {
        return magicWandDesc;
    }

    public void setMagicWandDesc(String magicWandDesc) {
        this.magicWandDesc = magicWandDesc;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
