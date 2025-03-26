package com.citycite_api.report.dto;

import lombok.Data;

@Data
public class ReportVehicleDTO { // DTO postfix is used to indicate it is used for both requests & responses

    private String manufacturer;
    private String model;
    private Integer year;
    private String color;
    private VehicleLicensePlateDTO vehicleLicensePlateDTO;

}
