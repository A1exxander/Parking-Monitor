package com.citycite_api.report.dto;

import lombok.Data;

@Data
public class ReportAddressDTO {

    private String streetLine;
    private String city;
    private String stateInitials;
    private String zipcode;
    private AddressCoordinatesDTO addressCoordinates;
    private String notes;

}
