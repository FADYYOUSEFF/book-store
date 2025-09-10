package com.example.book_store.entity;

import com.example.book_store.enums.BorrowingStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID BorrowingId;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;

    @Enumerated(EnumType.STRING)
    private BorrowingStatus status;

    public Borrowing() {
    }

    public Borrowing(UUID borrowingId, BookCopy bookCopy, UserApp user, BorrowingStatus status) {
        BorrowingId = borrowingId;
        this.bookCopy = bookCopy;
        this.user = user;
        this.status = status;
    }

    public UUID getBorrowingId() {
        return BorrowingId;
    }

    public void setBorrowingId(UUID borrowingId) {
        BorrowingId = borrowingId;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public UserApp getUser() {
        return user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }

    public BorrowingStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowingStatus status) {
        this.status = status;
    }
}
