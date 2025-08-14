package com.financeapp.expense.api.v1.dtos;

import com.financeapp.expense.domain.ExpenseCategory;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ExpenseFilter(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate from,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate to,
        List<ExpenseCategory> categories,
        BigDecimal minAmount,
        BigDecimal maxAmount,
        String q
) {
}
