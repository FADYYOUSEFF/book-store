package com.example.book_store.controller;

import com.example.book_store.payload.LoginInput;
import com.example.book_store.payload.RegisterInput;
import com.example.book_store.response.LoginResponse;
import com.example.book_store.response.RegisterResponse;
import com.example.book_store.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Validated RegisterInput registerInput) {
        return authService.register(registerInput);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginInput loginInput) {
        return authService.login(loginInput);
    }
}
