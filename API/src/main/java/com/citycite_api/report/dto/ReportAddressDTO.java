package com.citycite_api.report.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReportAddressDTO {

    @NotNull @Size(min = 4, max = 64)
    private final String streetLine;

    @NotNull @Size(min = 2, max = 32)
    private final String city;

    @NotNull @Size(min = 2, max = 2)
    private final String stateInitials;

    @NotNull @Size(min = 5, max = 9)
    private final String zipcode;

    @NotNull @Size(max = 128)
    private final String notes;

}
