package com.financeapp.expense.api.v1;

import com.financeapp.expense.domain.Expense;
import com.financeapp.expense.domain.ExpenseCategory;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public final class ExpenseSpecs {
    private ExpenseSpecs() {}

    public static Specification<Expense> dateFrom(LocalDate from) {
        return (r, q, cb) -> from == null ? null : cb.greaterThanOrEqualTo(r.get("date"), from);
    }
    public static Specification<Expense> dateTo(LocalDate to) {
        return (r, q, cb) -> to == null ? null : cb.lessThanOrEqualTo(r.get("date"), to);
    }
    public static Specification<Expense> categoryIn(List<ExpenseCategory> categories) {
        return (r, q, cb) -> (categories == null || categories.isEmpty()) ? null : r.get("category").in(categories);
    }
    public static Specification<Expense> amountMin(BigDecimal min) {
        return (r, q, cb) -> min == null ? null : cb.ge(r.get("amount"), min);
    }
    public static Specification<Expense> amountMax(BigDecimal max) {
        return (r, q, cb) -> max == null ? null : cb.le(r.get("amount"), max);
    }
    public static Specification<Expense> qLike(String qtext) {
        return (r, q, cb) -> (qtext == null || qtext.isBlank()) ? null
                : cb.like(cb.lower(r.get("description")), "%" + qtext.toLowerCase() + "%");
    }
}
