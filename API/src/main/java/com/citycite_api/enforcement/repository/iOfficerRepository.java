package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Officer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iOfficerRepository extends CrudRepository<Officer, Integer> {}
