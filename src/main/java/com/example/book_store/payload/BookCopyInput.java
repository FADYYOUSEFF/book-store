package com.example.book_store.payload;

import com.example.book_store.enums.Status;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record BookCopyInput(
        Status status,
        String Location,
        UUID bookId
) {
}
