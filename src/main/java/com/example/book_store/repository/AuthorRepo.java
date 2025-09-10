package com.example.book_store.repository;

import com.example.book_store.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepo extends JpaRepository<Author, UUID> {
}
