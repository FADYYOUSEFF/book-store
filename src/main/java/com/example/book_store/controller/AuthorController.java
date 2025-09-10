package com.example.book_store.controller;

import com.example.book_store.entity.Author;
import com.example.book_store.payload.AuthorInput;
import com.example.book_store.service.AuthorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Controller
public class AuthorController {
    private final AuthorService authorService;
    AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }


    @QueryMapping
    public List<Author> authorFilter(@Argument @Validated AuthorInput input){
        return authorService.authorFilter(input);
    }

    @QueryMapping
    public Author getAuthorByID(@Argument @Validated UUID id){
        return authorService.getAuthorByID(id);
    }
}
