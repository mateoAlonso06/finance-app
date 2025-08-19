package com.financeapp.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Size(min = 3, max = 50)
        String username,
        @Size(min = 3, max = 50)
        @Email
        String email,
        @NotBlank
        @Size(min = 8)
        String password
) {
}
