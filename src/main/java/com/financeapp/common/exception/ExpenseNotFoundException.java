package com.financeapp.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseNotFoundException extends RuntimeException {
        public ExpenseNotFoundException(String message) {
            super(message);
        }
}
