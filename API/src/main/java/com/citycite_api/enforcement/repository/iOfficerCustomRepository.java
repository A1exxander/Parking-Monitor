package com.citycite_api.enforcement.repository;

import com.citycite_api.enforcement.entity.Officer;
import org.springframework.stereotype.Repository;

@Repository
public interface iOfficerCustomRepository {
    public Officer findProxyByID(Integer ID);
}
