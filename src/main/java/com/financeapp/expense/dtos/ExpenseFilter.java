package com.financeapp.expense.dtos;

import com.financeapp.expense.domain.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ExpenseFilter {
    private LocalDate from;
    private LocalDate to;
    private List<ExpenseCategory> categories;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String q;
}
