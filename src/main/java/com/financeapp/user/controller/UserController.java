package com.financeapp.user.controller;

import com.financeapp.user.dtos.UserCreateRequest;
import com.financeapp.user.dtos.UserResponse;
import com.financeapp.user.dtos.UserUpdateRequest;
import com.financeapp.user.service.iUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final iUserService uService;

    public UserController(iUserService iUserService) {
        this.uService = iUserService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        UserResponse user = uService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(@PageableDefault(size = 20, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserResponse> users = uService.getUsers(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest user) {
        UserResponse userResponse = uService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequest user) {
        UserResponse userUpdated = uService.updatePatchUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(userUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        uService.deleteUser(id);
        return ResponseEntity.ok("Se elimino correctamente el usuario con id " + id);
    }
}
