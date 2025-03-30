package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Jurisdiction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface iJurisdictionRepository extends JpaRepository<Jurisdiction, Integer> {
    public Set<Jurisdiction> findBy();
    public Jurisdiction findByCityIgnoreCaseAndStateInitialsIgnoreCase(String city, String stateInitials);
    public boolean existsByCityIgnoreCaseAndStateInitialsIgnoreCase(String city, String stateInitials);
    public Page<Jurisdiction> findByCityStartsWith(String city, Pageable page);
}
