package com.financeapp.expense.api.v1.dtos;

import com.financeapp.expense.domain.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ExpenseFilter(
        LocalDate from,
        LocalDate to,
        List<ExpenseCategory> categories,
        BigDecimal minAmount,
        BigDecimal maxAmount,
        String q
) {
}
