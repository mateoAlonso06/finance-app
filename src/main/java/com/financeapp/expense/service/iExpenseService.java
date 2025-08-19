package com.financeapp.expense.service;

import com.financeapp.expense.dtos.ExpenseCreateRequest;
import com.financeapp.expense.dtos.ExpenseFilter;
import com.financeapp.expense.dtos.ExpenseResponse;
import com.financeapp.expense.dtos.ExpenseUpdateRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface iExpenseService {
    ExpenseResponse createExpense(ExpenseCreateRequest request);

    ExpenseResponse getExpenseById(UUID id);

    ExpenseResponse updateExpense(UUID id, ExpenseUpdateRequest request);

    void deleteExpense(UUID id);

    Page<ExpenseResponse> getExpensesByFilter(ExpenseFilter f, Pageable pageable);

    Page<ExpenseResponse> getExpensesByUser(UUID id, Pageable pageable);
}
