package com.financeapp.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Instant timeStamp;
    private String message;
}
