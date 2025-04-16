package com.citycite_api.report.mapper;

import com.citycite_api.report.dto.*;
import com.citycite_api.report.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface iReportMapper { //TODO : Make 2 seperate mappers for ReportAddress & ReportVehicle

    @Mapping(target = "ID", ignore = true)
    @Mapping(target = "violationDescription", source = "violationDescription")
    @Mapping(target = "reportAddress.streetLine", source = "reportAddress.streetLine")
    @Mapping(target = "reportAddress.zipcode", source = "reportAddress.zipcode")
    @Mapping(target = "reportAddress.jurisdiction", source = "reportAddress.jurisdiction")
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
    Report reportRequestToReport(ReportRequest reportRequest);

    @Mapping(target = "ID", source = "ID")
    @Mapping(target = "violationDescription", source = "violationDescription")
    @Mapping(target = "reportVehicle.manufacturer", source = "reportVehicle.manufacturer")
    @Mapping(target = "reportVehicle.model", source = "reportVehicle.model")
    @Mapping(target = "reportVehicle.year", source = "reportVehicle.year")
    @Mapping(target = "reportVehicle.color", source = "reportVehicle.color")
    @Mapping(target = "reportVehicle.vehicleLicensePlate.number", source = "reportVehicle.vehicleLicensePlate.number")
    @Mapping(target = "reportVehicle.vehicleLicensePlate.stateInitials", source = "reportVehicle.vehicleLicensePlate.stateInitials")
    @Mapping(target = "reportAddress.streetLine", source = "reportAddress.streetLine")
    @Mapping(target = "reportAddress.zipcode", source = "reportAddress.zipcode")
    @Mapping(target = "reportAddress.jurisdiction", source = "reportAddress.jurisdiction")
    @Mapping(target = "reportAddress.addressCoordinates.latitude", source = "reportAddress.coordinates.latitude")
    @Mapping(target = "reportAddress.addressCoordinates.longitude", source = "reportAddress.coordinates.longitude")
    @Mapping(target = "reportAddress.notes", source = "reportAddress.notes")
    @Mapping(target = "submittingUser", source = "submittingUser")
    @Mapping(target = "respondingOfficer", source = "respondingOfficer")
    @Mapping(target = "resolutionStatus", source = "resolutionStatus")
    @Mapping(target = "resolutionNotes", source = "resolutionNotes")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ReportResponse reportToReportResponse(Report report);

}