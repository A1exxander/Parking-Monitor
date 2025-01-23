package com.citycite_api.enforcement.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotNull;

@Data
public class JurisdictionResponse {

    @NotNull
    private Integer ID;

    @NotNull
    @Length(min = 2, max = 32)
    private String city;

    @NotNull
    @Length(min = 2, max = 2)
    private String stateInitials;

}
