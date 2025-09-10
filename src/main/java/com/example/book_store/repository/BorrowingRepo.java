package com.example.book_store.repository;

import com.example.book_store.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BorrowingRepo extends JpaRepository<Borrowing, UUID> {
}
