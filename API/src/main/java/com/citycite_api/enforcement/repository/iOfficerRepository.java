package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iOfficerRepository extends JpaRepository<Officer, Integer> {}
