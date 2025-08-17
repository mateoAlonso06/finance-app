package com.financeapp.expense.service;

import com.financeapp.expense.api.v1.dtos.ExpenseCreateRequest;
import com.financeapp.expense.api.v1.dtos.ExpenseFilter;
import com.financeapp.expense.api.v1.dtos.ExpenseResponse;
import com.financeapp.expense.api.v1.dtos.ExpenseUpdateRequest;

import com.financeapp.expense.domain.ExpenseCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface iExpenseService {
    ExpenseResponse createExpense(ExpenseCreateRequest request);

    ExpenseResponse getExpenseById(UUID id);

    ExpenseResponse updateExpense(UUID id, ExpenseUpdateRequest request);

    void deleteExpense(UUID id);

    Page<ExpenseResponse> getExpensesByFilter(ExpenseFilter f, Pageable pageable);

    BigDecimal getTotalAmount(LocalDate from, LocalDate to);

    Map<ExpenseCategory, BigDecimal> getTotalAmountByCategory(LocalDate from, LocalDate to);
}
