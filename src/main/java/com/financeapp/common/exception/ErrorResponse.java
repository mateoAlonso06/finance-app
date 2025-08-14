package com.financeapp.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
public class ErrorResponse {
    private Instant timeStamp;
    private String message;
    private String details;

    public ErrorResponse(Instant timeStamp, String details) {
        this.timeStamp = timeStamp;
        this.details = details;
    }

    public ErrorResponse(Instant timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
