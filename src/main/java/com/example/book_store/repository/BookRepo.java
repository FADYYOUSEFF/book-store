package com.example.book_store.repository;

import com.example.book_store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepo extends JpaRepository<Book,UUID> {
}
