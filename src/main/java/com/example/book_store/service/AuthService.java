package com.example.book_store.service;

import com.example.book_store.entity.UserApp;
import com.example.book_store.exception.EmailIsAlreadyUsedException;
import com.example.book_store.payload.LoginInput;
import com.example.book_store.payload.RegisterInput;
import com.example.book_store.response.LoginResponse;
import com.example.book_store.response.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserAppService userAppService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    AuthService(UserAppService userAppService, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userAppService = userAppService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<RegisterResponse> register(RegisterInput registerInput) {
        UserApp userApp = userAppService.saveNewUser(registerInput);
        RegisterResponse response = new RegisterResponse(userApp.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<LoginResponse> login(LoginInput loginInput) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginInput.email(), loginInput.password()));
        if (authentication.isAuthenticated()) {
            UserApp userApp = userAppService.findByEmail(loginInput.email()).orElseThrow(
                    () -> new EmailIsAlreadyUsedException("The give email is already used")
            );
            LoginResponse response = new LoginResponse(jwtService.generateToken(userApp.getEmail()), userApp.getId(), userApp.getEmail());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        throw new BadCredentialsException("Bad Credentials");
    }
}
