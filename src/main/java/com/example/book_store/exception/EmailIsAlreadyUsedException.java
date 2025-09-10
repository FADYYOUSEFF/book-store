package com.example.book_store.exception;

public class EmailIsAlreadyUsedException extends RuntimeException {
    public EmailIsAlreadyUsedException() {
    }

    public EmailIsAlreadyUsedException(String message) {
        super(message);
    }
}
