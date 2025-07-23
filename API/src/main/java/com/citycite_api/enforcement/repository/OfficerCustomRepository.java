package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Officer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OfficerCustomRepository implements iOfficerCustomRepository { // Maybe refactor this class into a global util inside of commons

    @PersistenceContext
    private EntityManager entityManager;

    public Officer findProxyByID(Integer ID) {
        return entityManager.getReference(Officer.class, ID);
    }

}
