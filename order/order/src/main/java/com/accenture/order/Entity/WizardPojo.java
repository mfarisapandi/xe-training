package com.accenture.order.Entity;

import lombok.*;

import java.time.*;

@Getter
@Setter
public class WizardPojo {


    private int wizardID;
    private String wizardName;
    private int wizardAge;
    private LocalDate joinedDate;
    private String isActive;
}
