package com.financeapp.expense.service;

import com.financeapp.common.exception.expenses.ExpenseNotFoundException;
import com.financeapp.common.exception.users.UserNotFoundException;
import com.financeapp.expense.dtos.ExpenseCreateRequest;
import com.financeapp.expense.dtos.ExpenseFilter;
import com.financeapp.expense.dtos.ExpenseResponse;
import com.financeapp.expense.dtos.ExpenseUpdateRequest;
import com.financeapp.expense.domain.Expense;
import com.financeapp.expense.helpers.ExpenseSpecs;
import com.financeapp.expense.mapper.ExpenseMapper;
import com.financeapp.expense.repository.ExpenseRepository;

import com.financeapp.user.domain.User;
import com.financeapp.user.repository.UserRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
public class ExpenseService implements iExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseMapper expenseMapper, ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseMapper = expenseMapper;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseResponse createExpense(ExpenseCreateRequest request) {
        Expense expense = expenseMapper.toEntity(request);
        User user = userRepository.findById(request.idUser())
                                    .orElseThrow(() -> new UserNotFoundException(request.idUser()));
        expense.setUser(user);

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

        checkFieldsOfRequest(expense, request);

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

    @Override
    public Page<ExpenseResponse> getExpensesByUser(UUID id, Pageable pageable) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        Page<Expense> expenses = expenseRepository.findByUser(user, pageable);
        return expenses.map(expenseMapper::toResponse);
    }

    private void checkFieldsOfRequest(Expense expense, ExpenseUpdateRequest request) {
        if (request.date() != null) {
            expense.setDate(request.date());
        }
        if (request.amount() != null) {
            expense.setAmount(request.amount());
        }
        if (request.category() != null) {
            expense.setCategory(request.category());
        }
        if (request.description() != null && !request.description().isBlank()) {
            expense.setDescription(request.description());
        }
    }
}
