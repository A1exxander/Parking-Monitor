package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportMapResponse {

    @NotNull
    @Min(1)
    private Integer reportID;

    @NotNull
    @Valid
    private AddressCoordinatesDTO addressCoordinates;

}
