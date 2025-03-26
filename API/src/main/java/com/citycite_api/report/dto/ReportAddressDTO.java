package com.citycite_api.report.dto;

import lombok.Data;

@Data
public class ReportAddressDTO {

    private String streetLine;
    private Integer jurisdictionID;
    private String stateInitials;
    private String city;
    private String zipcode;
    private AddressCoordinatesDTO addressCoordinates;
    private String notes;

}
