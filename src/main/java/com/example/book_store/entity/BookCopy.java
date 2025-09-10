package com.example.book_store.entity;

import com.example.book_store.enums.Status;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookCopyId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String location;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookCopy() {
    }

    public BookCopy(UUID bookCopyId, Status status, String location, Book book) {
        this.bookCopyId = bookCopyId;
        this.status = status;
        this.location = location;
        this.book = book;
    }

    public UUID getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(UUID bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
