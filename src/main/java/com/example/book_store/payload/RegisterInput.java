package com.example.book_store.payload;
import com.example.book_store.validation.CustomNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


public record RegisterInput(
        @CustomNotNull(fieldName = "first name")
        String firstName,
        @CustomNotNull(fieldName = "last name")
        String lastName,
        @CustomNotNull(fieldName = "email")
        @Email(message = "Email is not valid")
        String email,
        @CustomNotNull(fieldName = "password")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "password is not valid")
        String password,
        @CustomNotNull(fieldName = "role")
        String role
) {
}
