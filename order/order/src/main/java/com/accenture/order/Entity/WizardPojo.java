package com.accenture.order.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.*;

@Getter
@Setter
public class WizardPojo {

    @JsonProperty("wizard_id")
    private Integer wizard_id;

    @JsonProperty("wizard_name")
    private String wizard_name;

    @JsonProperty("wizard_age")
    private Integer wizard_age;

    @JsonProperty("joined_date")
    private LocalDate joined_date;

    @JsonProperty("is_active")
    private String is_active;
}
