package com.financeapp.expense.api.dto;

import com.financeapp.expense.domain.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        LocalDate date,
        BigDecimal amount,
        ExpenseCategory category,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
}
