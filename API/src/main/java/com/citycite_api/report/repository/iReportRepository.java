package com.citycite_api.report.repository;

import com.citycite_api.report.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iReportRepository extends JpaRepository<Report, Integer> {
    Page<Report> findBySubmittingUser_ID(Pageable pageable, Integer userId);
    Page<Report> findByRespondingOfficer_ID(Pageable pageable, Integer officerId);
}