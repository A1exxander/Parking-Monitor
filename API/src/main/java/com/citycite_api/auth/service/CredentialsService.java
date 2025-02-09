package com.citycite_api.auth.service;

import com.citycite_api.auth.entity.Credentials;
import com.citycite_api.auth.mapper.iCredentialsMapper;
import com.citycite_api.auth.repository.iCredentialsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@Transactional
@AllArgsConstructor @Getter @Setter
public class CredentialsService implements iCredentialsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private iCredentialsRepository credentialsRepository;

    @Autowired
    private iCredentialsMapper credentialsMapper;

    @Override
    public Credentials findByEmailAddress(String emailAddress) {
        return credentialsRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public boolean emailAddressExists(String emailAddress) {
        return credentialsRepository.existsByEmailAddress(emailAddress);
    }

    @Override
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
