package com.citycite_api.auth.service;

import com.citycite_api.auth.entity.Credentials;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iCredentialsService {
    public Credentials findByEmailAddress(String emailAddress);
    public boolean emailAddressExists(String emailAddress);
    public String hashPassword(String rawPassword);
}
