package com.example.book_store.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record BorrowBookInput(
        @Email(message = "Email is not valid")
        String userEmail,
        @Pattern(
                regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
                message = "Must be a valid UUID"
        )
        UUID bookCopyId
) {
}
