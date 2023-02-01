package com.accenture.magicWand.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "magic_wand_catalogue")
@Data
public class MagicWand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magic_wand_id")
    private int magicWandID;
    @Column(name = "magic_wand_name")
    private String magicWandName;
    @Column(name = "magic_wand_desc")
    private String magicWandDesc;
    @Column(name = "age_limit")
    private int ageLimit;
    @Column(name = "wand_stock")
    private int wandStock;

    
    public MagicWand() {
    }

    public MagicWand(int magicWandID, String magicWandName, String magicWandDesc, int ageLimit, int wandStock) {
        this.magicWandID = magicWandID;
        this.magicWandName = magicWandName;
        this.magicWandDesc = magicWandDesc;
        this.ageLimit = ageLimit;
        this.wandStock = wandStock;
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

    public int getWandStock() {
        return wandStock;
    }

    public void setWandStock(int wandStock) {
        this.wandStock = wandStock;
    }
}
