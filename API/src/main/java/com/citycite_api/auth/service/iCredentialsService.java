package com.citycite_api.auth.service;

import com.citycite_api.auth.entity.Credentials;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface iCredentialsService {
    public Credentials findByEmailAddress(@NotNull @Email String emailAddress);
    public boolean emailAddressExists(@NotNull @Email String emailAddress);
    public String hashPassword(@NotNull @Min(8) String rawPassword);
}
