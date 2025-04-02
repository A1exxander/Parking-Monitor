package com.citycite_api.report.mapper;

import com.citycite_api.report.dto.*;
import com.citycite_api.report.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface iReportMapper {

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "violationDescription", source = "violationDescription")

    @Mapping(target = "reportAddress.streetLine", source = "reportAddress.streetLine")
    @Mapping(target = "reportAddress.zipcode", source = "reportAddress.zipcode")
    @Mapping(target = "reportAddress.jurisdiction.stateInitials", source = "reportAddress.jurisdiction.stateInitials")
    @Mapping(target = "reportAddress.coordinates.latitude", source = "reportAddress.addressCoordinates.latitude")
    @Mapping(target = "reportAddress.coordinates.longitude", source = "reportAddress.addressCoordinates.longitude")
    @Mapping(target = "reportAddress.notes", source = "reportAddress.notes")

    @Mapping(target = "reportVehicle.manufacturer", source = "reportVehicle.manufacturer")
    @Mapping(target = "reportVehicle.model", source = "reportVehicle.model")
    @Mapping(target = "reportVehicle.year", source = "reportVehicle.year")
    @Mapping(target = "reportVehicle.color", source = "reportVehicle.color")
    @Mapping(target = "reportVehicle.vehicleLicensePlate.number", source = "reportVehicle.vehicleLicensePlate.number")
    @Mapping(target = "reportVehicle.vehicleLicensePlate.stateInitials", source = "reportVehicle.vehicleLicensePlate.stateInitials")

    @Mapping(target = "submittingUser", ignore = true)
    @Mapping(target = "respondingOfficer", ignore = true)
    @Mapping(target = "reportImages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Report reportRequestToReport(ReportRequest request);

}