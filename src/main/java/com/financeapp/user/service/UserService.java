package com.financeapp.user.service;

import com.financeapp.common.exception.UserNotFoundException;
import com.financeapp.user.dtos.UserCreateRequest;
import com.financeapp.user.dtos.UserResponse;
import com.financeapp.user.dtos.UserUpdateRequest;
import com.financeapp.user.domain.Role;
import com.financeapp.user.domain.User;
import com.financeapp.user.mapper.UserMapper;
import com.financeapp.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements iUserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toResponse(user);
    }

    @Override
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(mapper::toResponse);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        User toSave = mapper.toEntity(userCreateRequest);
        toSave.setRole(Role.USER);
        User saved = userRepository.save(toSave);
        return mapper.toResponse(saved);
    }

    @Override
    public UserResponse updatePatchUser(UUID id, UserUpdateRequest req) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (req.username() != null && !req.username().isBlank()) user.setUsername(req.username());

        if (req.email() != null && !req.email().isBlank()) user.setEmail(req.email());

        if (req.firstname() != null && !req.firstname().isBlank()) user.setFirstName(req.firstname());

        if (req.lastname() != null && !req.lastname().isBlank()) user.setLastName(req.lastname());

        return mapper.toResponse(userRepository.save(user));
    }
}
