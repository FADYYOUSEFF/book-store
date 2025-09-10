package com.example.book_store.exception.rest;

import org.springframework.http.HttpStatus;

public record ExceptionResponse(
        String message,
        HttpStatus httpStatus
) {
}
