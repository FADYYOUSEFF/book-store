package com.example.book_store.response;

import com.example.book_store.entity.BookCopy;
import com.example.book_store.enums.BorrowingStatus;

import java.util.UUID;

public record BorrowBookResponse(
        UUID borrowingId,
        String userEmail,
        BookCopy bookCopy,
        BorrowingStatus borrowingStatus
) {
}
