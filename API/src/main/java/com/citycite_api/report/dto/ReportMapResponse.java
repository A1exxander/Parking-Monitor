package com.citycite_api.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportMapResponse {

    private Integer reportID;
    private AddressCoordinatesDTO addressCoordinates;

}
