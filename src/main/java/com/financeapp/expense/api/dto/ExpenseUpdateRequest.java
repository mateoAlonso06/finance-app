package com.financeapp.expense.api.dto;

import com.financeapp.expense.domain.ExpenseCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseUpdateRequest(
        @NotNull LocalDate date,
        @NotNull @DecimalMin("0.01")
        BigDecimal amount,
        @NotNull
        ExpenseCategory category,
        @Size(max = 300) String description
) {
}
