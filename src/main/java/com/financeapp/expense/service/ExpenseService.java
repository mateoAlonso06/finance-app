package com.financeapp.expense.service;

import com.financeapp.expense.api.dto.ExpenseCreateRequest;
import com.financeapp.expense.api.dto.ExpenseFilter;
import com.financeapp.expense.api.dto.ExpenseResponse;
import com.financeapp.expense.api.dto.ExpenseUpdateRequest;
import com.financeapp.expense.domain.Expense;
import com.financeapp.expense.domain.ExpenseCategory;
import com.financeapp.expense.mapper.ExpenseMapper;
import com.financeapp.expense.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpenseService<ExpenseResonse> implements iExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseMapper expenseMapper, ExpenseRepository expenseRepository) {
        this.expenseMapper = expenseMapper;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResponse createExpense(ExpenseCreateRequest request) {
        Expense expense = expenseMapper.toEntity(request);
        Expense savedEntity = expenseRepository.save(expense);
        return expenseMapper.toResponse(savedEntity);
    }

    @Override
    public ExpenseResponse getExpenseById(UUID id) {
        Expense expense = expenseRepository.findById(id).orElse(null);
        return expenseMapper.toResponse(expense);
    }

    @Override
    public List<ExpenseResponse> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenseMapper.toResponseList(expenses);
    }

    @Override
    public ExpenseResponse updateExpense(UUID id, ExpenseUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteExpense(UUID id) {

    }

    @Override
    public List<ExpenseResponse> findExpenses(ExpenseFilter filter) {
        return List.of();
    }

    @Override
    public BigDecimal getTotalAmount(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public Map<ExpenseCategory, BigDecimal> getTotalAmountByCategory(LocalDate from, LocalDate to) {
        return null;
    }
}
