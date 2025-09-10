package com.example.book_store.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;
    private String title;
    private String language;
    private String edition;
    @ManyToMany(mappedBy = "books",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Author> authors;

    public Book() {
    }

    public Book(UUID bookId, String title, String language, String edition, List<Author> authors) {
        this.bookId = bookId;
        this.title = title;
        this.language = language;
        this.edition = edition;
        this.authors = authors;
    }

    public Book(String title, String language, String edition) {
        this.title = title;
        this.language = language;
        this.edition = edition;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
