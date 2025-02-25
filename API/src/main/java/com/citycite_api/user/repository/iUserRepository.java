package com.citycite_api.user.repository;

import com.citycite_api.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface iUserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByCredentialsEmailAddress(String emailAddress);
}
