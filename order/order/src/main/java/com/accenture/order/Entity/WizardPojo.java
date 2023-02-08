package com.accenture.order.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
@Setter
public class WizardPojo {


    @JsonProperty("wizardID")
    private int wizardID;
    @JsonProperty("wizardName")
    private String wizardName;
    @JsonProperty("wizardAge")
    private int wizardAge;
    @JsonProperty("joinedDate")
    private LocalDate joinedDate;
    @JsonProperty("isActive")
    private String isActive;
}
