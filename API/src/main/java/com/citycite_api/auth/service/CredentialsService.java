package com.citycite_api.auth.service;

import com.citycite_api.auth.entity.Credentials;
import com.citycite_api.auth.repository.iCredentialsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CredentialsService implements iCredentialsService {

    @Autowired
    private iCredentialsRepository credentialsRepository;

    @Override
    public Credentials findByEmailAddress(@NotNull @Email String emailAddress) {
        return credentialsRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public boolean emailAddressExists(@NotNull @Email String emailAddress) {
        return credentialsRepository.existsByEmailAddress(emailAddress);
    }
    
}
