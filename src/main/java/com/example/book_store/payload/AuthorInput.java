package com.example.book_store.payload;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record AuthorInput(
        @Pattern(
                regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$",
                message = "Must be a valid UUID"
        )
        UUID authorId,
        String name,
        String nationality
) {
}
