package com.citycite_api.auth.mapper;

import com.citycite_api.auth.dto.CredentialsRequest;
import com.citycite_api.auth.entity.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface iCredentialsMapper {
    public Credentials credentialsDTOToCredentials(CredentialsRequest credentialsRequest);
}
