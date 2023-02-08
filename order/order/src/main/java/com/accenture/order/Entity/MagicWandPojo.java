package com.accenture.order.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
public class MagicWandPojo {


    @JsonProperty("magicWandID")
    private int magicWandID;
    @JsonProperty("magicWandName")
    private String magicWandName;
    @JsonProperty("magicWandDesc")
    private String magicWandDesc;
    @JsonProperty("ageLimit")
    private int ageLimit;
    @JsonProperty("wandStock")
    private int wandStock;
}
