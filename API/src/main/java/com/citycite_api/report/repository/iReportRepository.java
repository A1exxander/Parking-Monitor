package com.citycite_api.report.repository;

import com.citycite_api.report.entity.Report;
import com.citycite_api.report.entity.ReportStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface iReportRepository extends JpaRepository<Report, Integer> {

    public Optional<Report> findBySubmittingUser_ID(Integer userId);
    public Page<Report> findAllBySubmittingUser_ID(Pageable pageable, Integer userId);
    public Page<Report> findAllByRespondingOfficer_ID(Pageable pageable, Integer officerId);

    @Query(value = """
        SELECT * FROM Report r
        WHERE 
            CASE
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
    public List<Report> findByReportStatus(@Param("status") ReportStatus status);

    @Query(value = """
        SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END
        FROM Report R
        WHERE 
            R.respondingOfficerID = :officerID
            AND r.CreatedAt >= NOW() - INTERVAL 2 HOUR 
            AND r.ResolutionStatus IS NULL      
    """, nativeQuery = true)
    public boolean findByReportStatusAndRespondingOfficerID(@Param("status") ReportStatus status, @Param("respondingOfficerID") Integer officerID);

}