package com.citycite_api.report.dto;

import com.citycite_api.enforcement.dto.JurisdictionDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportAddressDTO {

    private Integer ID;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 64)
    private String streetLine;

    @NotNull
    @Valid
    private JurisdictionDTO jurisdiction;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$")
    private String zipcode;

    @NotNull
    @Valid
    private AddressCoordinatesDTO addressCoordinates;

    @Size(max = 128)
    private String notes;

}
