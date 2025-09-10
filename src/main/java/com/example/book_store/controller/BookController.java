package com.example.book_store.controller;

import com.example.book_store.entity.Book;
import com.example.book_store.payload.BookInput;
import com.example.book_store.payload.SaveBookInput;
import com.example.book_store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @MutationMapping
    public Boolean deleteBookById(@Argument UUID id){
        return bookService.deleteBookById(id);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @MutationMapping
    public Book saveBook(@Argument SaveBookInput input){
        return bookService.saveBook(input);
    }

    @QueryMapping
    public Book getBookById(@Argument UUID id){
        return bookService.getBookById(id);
    }

    @QueryMapping
    public List<Book> bookFilter(@Argument @Validated BookInput input){
        return bookService.bookFilter(input);
    }

}
