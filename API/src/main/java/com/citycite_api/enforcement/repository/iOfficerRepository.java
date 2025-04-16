package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface iOfficerRepository extends JpaRepository<Officer, Integer> {
    @Query("SELECT o FROM Officer o WHERE o.id = :officerID")
    Officer findOfficerById(@Param("officerID") Integer officerID);
}
