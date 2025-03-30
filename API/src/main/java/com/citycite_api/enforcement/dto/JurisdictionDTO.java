package com.citycite_api.enforcement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class JurisdictionDTO {

    private Integer ID;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 32)
    private String city;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 2)
    private String stateInitials;

}
