package com.citycite_api.report.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class ReportGeoResponse {

    @NotNull
    @Min(1)
    private Integer reportID;

    @NotNull
    @Valid
    private AddressCoordinatesDTO addressCoordinates;

    @NotNull
    private Instant expiresAt;

}
