package com.example.book_store.controller;

import com.example.book_store.entity.BookCopy;
import com.example.book_store.payload.BookCopyInput;
import com.example.book_store.payload.SaveBookCopyInput;
import com.example.book_store.service.BookCopyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Controller
public class CopyBookController {

    private final BookCopyService bookCopyService;

    public CopyBookController(BookCopyService bookCopyService){
        this.bookCopyService = bookCopyService;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @MutationMapping
    public BookCopy saveBookCopy(SaveBookCopyInput input){
        return bookCopyService.saveBookCopyInput(input);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @MutationMapping Boolean deleteBookCopyById(@Argument UUID id){
        return bookCopyService.deleteBookCopyById(id);
    }

    @QueryMapping
    public BookCopy getBookCopyById(@Argument UUID id){
        return bookCopyService.getBookCopyById(id);
    }

    @QueryMapping
    public List<BookCopy> bookCopyFilter(@Argument @Validated BookCopyInput input){
        return bookCopyService.bookCopyFilter(input);
    }
}
