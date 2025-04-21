package com.citycite_api.report.repository;

import com.citycite_api.report.entity.Report;
import com.citycite_api.report.entity.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface iReportRepository extends JpaRepository<Report, Integer> {

    public Optional<Report> findBySubmittingUser_ID(Integer userId);
    public Page<Report> findAllBySubmittingUser_ID(Pageable pageable, Integer userId);
    public Page<Report> findAllByRespondingOfficer_ID(Pageable pageable, Integer officerId);

    // TODO: Switch these queries to not have time of an open report or cooldown baked into them, use an environment variable
    @Query(value = """
        SELECT * 
        FROM Report r
        WHERE CASE
            WHEN r.RespondingOfficerID IS NULL 
                AND r.CreatedAt >= NOW() - INTERVAL 2 HOUR 
                AND r.ResolutionStatus IS NULL THEN 'OPEN'
            WHEN r.RespondingOfficerID IS NOT NULL 
                AND r.CreatedAt >= NOW() - INTERVAL 2 HOUR 
                AND r.ResolutionStatus IS NULL THEN 'IN_PROCESS'
            WHEN r.CreatedAt < NOW() - INTERVAL 2 HOUR 
                AND r.ResolutionStatus IS NULL THEN 'TIMED_OUT'
            WHEN r.ResolutionStatus IS NOT NULL THEN 'RESOLVED'
        END = :#{#status.name()}
    """, nativeQuery = true)
    public Page<Report> findByReportStatus(@Param("status") ReportStatus status, Pageable pageable);

    @Query(value = """
        SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
        FROM Report r
        WHERE 
            r.respondingOfficerID = :officerID
            AND r.CreatedAt >= NOW() - INTERVAL 2 HOUR 
            AND r.ResolutionStatus IS NULL      
    """, nativeQuery = true)
    public boolean existsUnresolvedReport(@Param("status") ReportStatus status, @Param("respondingOfficerID") Integer officerID);

    @Query(value = """
        SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
        FROM Report r
        INNER JOIN ReportVehicle rv ON r.VehicleID = rv.ID 
        INNER JOIN LicensePlate lp ON rv.LicensePlateID = lp.ID
        WHERE
             lp.Number = :number
             AND lp.StateInitials = :state
             AND r.CreatedAt >= NOW() - INTERVAL 8 HOUR 
    """, nativeQuery = true)
    public boolean existsByVehicleLicensePlateWithinLast8Hours(@Param("number") String number, @Param("state") String state);

}