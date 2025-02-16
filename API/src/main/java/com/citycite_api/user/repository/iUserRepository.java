package com.citycite_api.user.repository;

import com.citycite_api.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUserRepository extends CrudRepository<User, Integer> {
    public User findByCredentialsEmailAddress(String emailAddress);
}
