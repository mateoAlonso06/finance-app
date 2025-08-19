package com.financeapp.user.service;

import com.financeapp.user.dtos.UserCreateRequest;
import com.financeapp.user.dtos.UserResponse;
import com.financeapp.user.dtos.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface iUserService {
    UserResponse getUser(UUID id);

    Page<UserResponse> getUsers(Pageable pageable);

    void deleteUser(UUID id);

    UserResponse createUser(UserCreateRequest userCreateRequest);

    UserResponse updatePatchUser(UUID id, UserUpdateRequest userUpdateRequest);
}
