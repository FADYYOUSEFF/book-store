package com.example.book_store.payload;

import java.util.UUID;

public record BookInput(
        UUID bookId,
        String title,
        String language,
        String edition
) {
}
