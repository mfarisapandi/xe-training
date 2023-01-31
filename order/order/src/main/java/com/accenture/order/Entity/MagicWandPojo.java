package com.accenture.order.Entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@Setter
public class MagicWandPojo {

    @JsonProperty("magic_wand_id")
    private Integer magic_wand_id;

    @JsonProperty("magic_wand_name")
    private String magic_wand_name;

    @JsonProperty("magic_wand_desc")
    private String magic_wand_desc;

    @JsonProperty("age_limit")
    private Integer age_limit;

    @JsonProperty("stock")
    private Integer stock;
}
