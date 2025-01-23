package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Jurisdiction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface iJurisdictionRepository extends CrudRepository<Jurisdiction, Integer> {
    public List<Jurisdiction> findAll();
    public boolean existsByCityAndStateInitials(String city, String stateInitials);
}
