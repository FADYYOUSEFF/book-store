package com.example.book_store.exception;

public class BookAlreadyBorrowedException extends RuntimeException {
    public BookAlreadyBorrowedException() {
    }

    public BookAlreadyBorrowedException(String message) {
        super(message);
    }
}
