package com.citycite_api.enforcement.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor(force = true)
public class JurisdictionResponse {

    @NotNull
    private final Integer ID;

    @NotNull
    @Size(min = 2, max = 32)
    private final String city;

    @NotNull
    @Size(min = 2, max = 2)
    private final String stateInitials;

}
