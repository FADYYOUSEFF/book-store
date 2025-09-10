package com.example.book_store.service;

import com.example.book_store.entity.Book;
import com.example.book_store.entity.BookCopy;
import com.example.book_store.enums.Status;
import com.example.book_store.exception.RecordNotFoundException;
import com.example.book_store.payload.BookCopyInput;
import com.example.book_store.payload.SaveBookCopyInput;
import com.example.book_store.repository.BookCopyRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookCopyService {
    private final BookCopyRepo bookCopyRepo;
    private final BookService bookService;

    public BookCopyService(BookCopyRepo bookCopyRepo, BookService bookService) {
        this.bookCopyRepo = bookCopyRepo;
        this.bookService = bookService;
    }

    public BookCopy getBookCopyById(UUID id) {
        return bookCopyRepo.findById(id).orElseThrow(
                () -> new RecordNotFoundException(String.format("The give id: %s is Not Found", id))
        );
    }

    public List<BookCopy> bookCopyFilter(BookCopyInput input) {
        List<BookCopy> bookCopies = bookCopyRepo.findAll();
        for (BookCopy bookCopy:bookCopies){
            System.out.println(bookCopy.getBookCopyId()+"==================================\n");
        }
        return bookCopies.stream()
                .filter(bookCopy -> input.Location() == null || bookCopy.getLocation().equals(input.Location()))
                .filter(bookCopy -> input.status() == null || bookCopy.getStatus().equals(input.status()))
                .filter(bookCopy -> input.bookId() == null || bookCopy.getBook().getBookId().equals(input.bookId()))
                .toList();
    }

    public BookCopy saveBookCopyInput(SaveBookCopyInput input) {
        Book book = bookService.getBookById(input.bookId());
        BookCopy bookCopy = new BookCopy(null, Status.AVAILABLE,input.location(),book);
        return bookCopyRepo.save(bookCopy);
    }

    public Boolean deleteBookCopyById(UUID id){
        // check if the book copy id is existed
        getBookCopyById(id);
        bookCopyRepo.deleteById(id);
        return true;
    }

    public void saveAsBorrowed(BookCopy bookCopy) {
        bookCopy.setStatus(Status.BORROWED);
        bookCopyRepo.save(bookCopy);
    }

    public void saveAsReturned(BookCopy bookCopy) {
        bookCopy.setStatus(Status.AVAILABLE);
        bookCopyRepo.save(bookCopy);
    }
}
