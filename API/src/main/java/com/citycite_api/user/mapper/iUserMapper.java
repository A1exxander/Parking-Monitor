package com.citycite_api.user.mapper;

import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface iUserMapper {
    @Mapping(target = "birthDate", source = "birthDate")
    public User userRequestToUser(UserRequest userRequest);
}
