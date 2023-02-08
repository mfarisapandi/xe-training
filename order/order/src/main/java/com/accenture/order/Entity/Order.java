package com.accenture.order.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@Entity
@Table(name = "order_services")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderID;
    @Column(name = "wizard_id")
    private int wizardID;
    private String wizardName;
    private int wizardAge;
    private LocalDate joinedDate;
    @Column(name = "magic_wand_id")
    private int magicWandID;
    private String magicWandName;
    private String magicWandDesc;

    public Order() {
    }
}
