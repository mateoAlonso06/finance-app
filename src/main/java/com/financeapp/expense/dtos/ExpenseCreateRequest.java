package com.financeapp.expense.dtos;

import com.financeapp.expense.domain.ExpenseCategory;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseCreateRequest(
        @NotNull
        LocalDate date,
        @NotNull @DecimalMin("0.01") @Digits(integer = 12, fraction = 2)
        BigDecimal amount,
        @NotNull
        ExpenseCategory category,
        @Size(max = 300) @NotEmpty
        String description,
        @NotNull
        UUID idUser
) {
}
