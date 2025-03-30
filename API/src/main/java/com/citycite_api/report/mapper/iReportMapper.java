package com.citycite_api.report.mapper;

import com.citycite_api.enforcement.dto.JurisdictionDTO;
import com.citycite_api.enforcement.dto.OfficerResponse;
import com.citycite_api.enforcement.entity.Jurisdiction;
import com.citycite_api.enforcement.entity.Officer;
import com.citycite_api.report.dto.*;
import com.citycite_api.report.entity.*;
import com.citycite_api.user.dto.UserResponse;
import com.citycite_api.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface iReportMapper { // TODO: Fix this shit asap, so ugly & prob doesnt work properly but might

    iReportMapper INSTANCE = Mappers.getMapper(iReportMapper.class);

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "reportAddress", source = "reportAddress")
    @Mapping(target = "reportVehicle", source = "reportVehicle")
    @Mapping(target = "submittingUser", ignore = true)
    @Mapping(target = "respondingOfficer", ignore = true)
    @Mapping(target = "reportImages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public Report reportRequestToReport(ReportRequest request);

    @Mapping(target = "reportID", source = "ID")
    @Mapping(target = "submittingUser", source = "submittingUser")
    @Mapping(target = "reportAddress", source = "reportAddress")
    @Mapping(target = "reportVehicle", source = "reportVehicle")
    @Mapping(target = "respondingOfficer", source = "respondingOfficer")
    public ReportResponse reportToReportResponse(Report report);

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "jurisdiction", source = "jurisdiction") // Maps via mapJurisdiction()
    @Mapping(target = "coordinates", source = "addressCoordinates")
    @Mapping(target = "report", ignore = true)
    ReportAddress mapAddress(ReportAddressDTO reportAddressDTO);

    @Mapping(target = "ID", ignore = true)
    Jurisdiction mapJurisdiction(JurisdictionDTO jurisdictionDTO); // Auto-maps fields like stateInitials

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "vehicleLicensePlate", source = "vehicleLicensePlateDTO")
    @Mapping(target = "report", ignore = true)
    ReportVehicle mapVehicle(ReportVehicleDTO dto);

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "reportVehicle", ignore = true)
    VehicleLicensePlate mapLicensePlate(VehicleLicensePlateDTO dto);

    Coordinates mapCoordinates(AddressCoordinatesDTO dto);

    // Supporting mappings for nested objects
    @Mapping(target = "jurisdiction", source = "jurisdiction")
    @Mapping(target = "addressCoordinates", source = "coordinates")
    ReportAddressDTO reportAddressToDTO(ReportAddress reportAddress);

    @Mapping(target = "stateInitials", source = "stateInitials")  // Example field mapping
    JurisdictionDTO jurisdictionToDTO(Jurisdiction jurisdiction);

    @Mapping(target = "vehicleLicensePlateDTO", source = "vehicleLicensePlate")
    ReportVehicleDTO vehicleToDTO(ReportVehicle vehicle);

    @Mapping(target = "number", source = "number")
    VehicleLicensePlateDTO licensePlateToDTO(VehicleLicensePlate plate);

    @Mapping(target = "latitude", source = "latitude")
    @Mapping(target = "longitude", source = "longitude")
    AddressCoordinatesDTO coordinatesToDTO(Coordinates coordinates);

    // Add these if you need user/officer mappings
    @Mapping(target = "ID", source = "ID")
    UserResponse userToUserResponse(User user);

    @Mapping(target = "identificationNumber", source = "identificationNumber")
    OfficerResponse officerToOfficerResponse(Officer officer);

}
