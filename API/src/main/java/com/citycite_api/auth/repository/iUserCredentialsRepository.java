package com.citycite_api.auth.repository;

import com.citycite_api.auth.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUserCredentialsRepository extends CrudRepository<UserCredentials, Integer> {
    UserCredentials findByEmailAddress(String emailAddress);
}
