package com.example.book_store.response;

import java.util.UUID;

public record LoginResponse(
        String token,
        UUID id,
        String email
) {
}
