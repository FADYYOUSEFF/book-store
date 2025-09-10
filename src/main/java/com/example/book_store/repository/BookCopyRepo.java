package com.example.book_store.repository;

import com.example.book_store.entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCopyRepo extends JpaRepository<BookCopy, UUID> {
}
