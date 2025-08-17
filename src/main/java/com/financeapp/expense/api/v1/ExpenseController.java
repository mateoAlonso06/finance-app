package com.financeapp.expense.api.v1;

import com.financeapp.expense.api.v1.dtos.ExpenseCreateRequest;
import com.financeapp.expense.api.v1.dtos.ExpenseFilter;
import com.financeapp.expense.api.v1.dtos.ExpenseResponse;
import com.financeapp.expense.api.v1.dtos.ExpenseUpdateRequest;
import com.financeapp.expense.service.iExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final iExpenseService service;

    public ExpenseController(iExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ExpenseResponse>> getExpenses(
            @ModelAttribute ExpenseFilter expenseFilter,
            @PageableDefault(size = 20, sort = "date", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ExpenseResponse> expenses = service.getExpensesByFilter(expenseFilter, pageable);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable UUID id) {
        ExpenseResponse body = service.getExpenseById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody ExpenseCreateRequest exp) {
        ExpenseResponse response = service.createExpense(exp);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable UUID id) {
        service.deleteExpense(id);
        return ResponseEntity.ok("Se elimino correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpenseData(@PathVariable UUID id, @RequestBody ExpenseUpdateRequest exp) {
        ExpenseResponse body = service.updateExpense(id, exp);
        return ResponseEntity.ok(body);
    }
}
