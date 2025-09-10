package com.example.book_store.repository;

import com.example.book_store.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserAppRepo extends JpaRepository<UserApp, UUID> {
    Optional<UserApp> findByEmail(String email);
}
