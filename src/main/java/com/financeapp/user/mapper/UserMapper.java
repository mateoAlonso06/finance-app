package com.financeapp.user.mapper;

import com.financeapp.user.dtos.UserCreateRequest;
import com.financeapp.user.dtos.UserResponse;
import com.financeapp.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    User toEntity(UserCreateRequest user);

    @Mapping(source = "idUser", target = "idUser")
    UserResponse toResponse(User user);
}
