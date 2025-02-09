package com.citycite_api.user.mapper;

import com.citycite_api.user.dto.UserRequest;
import com.citycite_api.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface iUserMapper {
    public User userRequestToUser(UserRequest userRequest);
    public UserRequest userToUserRequest(User user);
}
