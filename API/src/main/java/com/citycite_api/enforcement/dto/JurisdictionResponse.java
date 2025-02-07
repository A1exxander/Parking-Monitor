package com.citycite_api.enforcement.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JurisdictionResponse {

    @NotNull
    private Integer ID;

    @NotNull
    @Size(min = 2, max = 32)
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String stateInitials;

}
