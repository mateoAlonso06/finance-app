package com.financeapp.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Size(min = 3, max = 100)
        String firstname,
        @Size(min = 3, max = 100)
        String lastname,
        @Size(min = 3, max = 50)
        String username,
        @Email
        String email
) {
}
