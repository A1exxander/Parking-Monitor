package com.citycite_api.auth.repository;

import com.citycite_api.auth.entity.Credentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCredentialsRepository extends CrudRepository<Credentials, Integer> {
    public Credentials findByEmailAddress(String emailAddress);
    public boolean existsByEmailAddress(String emailAddress);
}
