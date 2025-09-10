package com.example.book_store.service;

import com.example.book_store.entity.Author;
import com.example.book_store.exception.RecordNotFoundException;
import com.example.book_store.mapper.BookMapper;
import com.example.book_store.payload.BookInput;
import com.example.book_store.payload.SaveAuthorInput;
import com.example.book_store.payload.SaveBookInput;
import com.example.book_store.repository.BookRepo;
import com.example.book_store.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    BookService(BookRepo bookRepo,AuthorService authorService,BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.authorService=authorService;
        this.bookMapper=bookMapper;
    }

    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    public List<Book> bookFilter(BookInput bookInput) {
        List<Book> books = bookRepo.findAll();
        return books.stream()
                .filter(book-> bookInput.bookId()==null||book.getBookId().equals(bookInput.bookId()))
                .filter(book -> bookInput.title()==null||book.getTitle().equals(bookInput.title()))
                .filter(book -> bookInput.edition()==null||book.getEdition().equals(bookInput.edition()))
                .filter(book -> bookInput.language()==null||book.getLanguage().equals(bookInput.language()))
                .toList();
    }

    public Book getBookById(UUID id) {
        return bookRepo.findById(id).orElseThrow(
                ()-> new RecordNotFoundException(String.format("The give id: %s is Not Found",id))
        );
    }

    public boolean deleteBookById(UUID id) {
        bookRepo.findById(id).orElseThrow(
                ()-> new RecordNotFoundException(String.format("The give id: %s is Not Found",id))
        );
        bookRepo.deleteById(id);
        return true;
    }

    public Book saveBook(SaveBookInput input) {
        List<Author> authors = new ArrayList<>();
        for (SaveAuthorInput author : input.authors()){
            // In case the client did not provide id of author, we add the author as new author
            if(author.authorId() == null){
                authors.add(new Author(null,author.name(),author.nationality(),new ArrayList<>()));
            }
            // In case the client provided the id of author, we add the book to the exist author list
            else{
                Author existedAuthor = authorService.getAuthorByID(author.authorId());
                authors.add(existedAuthor);
            }
        }
        Book book = bookMapper.fromSaveBookInput(input);
        book.setAuthors(authors);
        for (Author author : authors) {
            author.getBooks().add(book);
        }
        book = bookRepo.save(book);
        return book;
    }
}
