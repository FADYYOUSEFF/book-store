package com.example.book_store.service;

import com.example.book_store.entity.Author;
import com.example.book_store.exception.RecordNotFoundException;
import com.example.book_store.payload.AuthorInput;
import com.example.book_store.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo){
        this.authorRepo = authorRepo;
    }

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthorByID(UUID id) {
        return authorRepo.findById(id).orElseThrow(
                ()-> new RecordNotFoundException(String.format("author id: %s is not found",id))
        );
    }

    @BatchMapping
    public List<Author> authorFilter(AuthorInput input) {
        List<Author> authors = authorRepo.findAll();
        return authors.stream()
                .filter(author -> input.authorId()==null||author.getAuthorId().equals(input.authorId()))
                .filter(author -> input.name()==null||author.getName().equals(input.name()))
                .filter(author -> input.nationality()==null||author.getNationality().equals(input.nationality()))
                .toList();
    }

}
