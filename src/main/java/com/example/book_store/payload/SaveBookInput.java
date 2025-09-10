package com.example.book_store.payload;

import java.util.List;

public record SaveBookInput(
        String title,
        String language,
        String edition,
        List<SaveAuthorInput> authors
) {
}


