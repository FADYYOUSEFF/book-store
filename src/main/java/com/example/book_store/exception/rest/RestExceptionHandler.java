package com.example.book_store.exception.rest;

import com.example.book_store.exception.EmailIsAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EmailIsAlreadyUsedException.class)
    public ResponseEntity<ExceptionResponse> emailAlreadyUsedExceptionHandler(EmailIsAlreadyUsedException ex) {
        return helper(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())

        );
        return helper(errors.toString(), HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<ExceptionResponse> helper(String message, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse(message, httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
