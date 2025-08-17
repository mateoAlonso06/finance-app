package com.financeapp.expense.service;

import com.financeapp.common.exception.ExpenseNotFoundException;
import com.financeapp.expense.api.v1.dtos.ExpenseCreateRequest;
import com.financeapp.expense.api.v1.dtos.ExpenseFilter;
import com.financeapp.expense.api.v1.dtos.ExpenseResponse;
import com.financeapp.expense.api.v1.dtos.ExpenseUpdateRequest;
import com.financeapp.expense.domain.Expense;
import com.financeapp.expense.domain.ExpenseCategory;
import com.financeapp.expense.api.v1.ExpenseSpecs;
import com.financeapp.expense.mapper.ExpenseMapper;
import com.financeapp.expense.repository.ExpenseRepository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Service
public class ExpenseService implements iExpenseService {
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
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));
        return expenseMapper.toResponse(expense);
    }

    @Override
    public ExpenseResponse updateExpense(UUID id, ExpenseUpdateRequest request) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));

        expenseMapper.updateEntityFromDto(request, expense);
        Expense saved = expenseRepository.save(expense);
        return expenseMapper.toResponse(saved);
    }

    @Override
    public void deleteExpense(UUID id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));

        expenseRepository.delete(expense);
    }

    @Override
    public Page<ExpenseResponse> getExpensesByFilter(ExpenseFilter f, Pageable pageable) {
        Specification<Expense> spec = Specification.allOf(
                ExpenseSpecs.dateFrom(f.getFrom()),
                ExpenseSpecs.dateTo(f.getTo()),
                ExpenseSpecs.categoryIn(f.getCategories()),
                ExpenseSpecs.amountMin(f.getMinAmount()),
                ExpenseSpecs.amountMax(f.getMaxAmount()),
                ExpenseSpecs.qLike(f.getQ())
        );
        Page<Expense> expenses = expenseRepository.findAll(spec, pageable);
        return expenses.map(expenseMapper::toResponse);
    }

    // TODO: Implementar estos metodos
    @Override
    public BigDecimal getTotalAmount(LocalDate from, LocalDate to) {
        return null;
    }

    @Override
    public Map<ExpenseCategory, BigDecimal> getTotalAmountByCategory(LocalDate from, LocalDate to) {
        return null;
    }
}
