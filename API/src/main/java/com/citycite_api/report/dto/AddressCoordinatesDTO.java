package com.citycite_api.report.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AddressCoordinatesDTO {

    private BigDecimal longitude;
    private BigDecimal latitude;

}
