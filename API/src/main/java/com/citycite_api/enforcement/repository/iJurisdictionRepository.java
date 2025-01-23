package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Jurisdiction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface iJurisdictionRepository extends CrudRepository<Jurisdiction, Integer> {
    public Set<Jurisdiction> findAll();
    public boolean existsByCityIgnoreCaseAndStateInitialsIgnoreCase(String city, String stateInitials);
}
