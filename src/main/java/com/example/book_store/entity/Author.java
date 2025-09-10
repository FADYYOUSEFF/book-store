package com.example.book_store.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID authorId;
    private String name;
    private String nationality;
    @ManyToMany
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    public Author() {
    }

    public Author(UUID authorId, String name, String nationality, List<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.nationality = nationality;
        this.books = books;
    }

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
