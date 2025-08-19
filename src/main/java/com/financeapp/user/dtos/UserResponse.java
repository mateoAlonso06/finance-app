package com.financeapp.user.dtos;

import java.util.UUID;

public record UserResponse(
        UUID idUser,
        String firstName,
        String lastName,
        String username,
        String email
) {
}
