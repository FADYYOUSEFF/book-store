package com.example.book_store.service;

import com.example.book_store.entity.BookCopy;
import com.example.book_store.entity.Borrowing;
import com.example.book_store.entity.UserApp;
import com.example.book_store.enums.BorrowingStatus;
import com.example.book_store.enums.Status;
import com.example.book_store.exception.BookAlreadyBorrowedException;
import com.example.book_store.exception.RecordNotFoundException;
import com.example.book_store.payload.BorrowBookInput;
import com.example.book_store.payload.ReturnBookInput;
import com.example.book_store.repository.BorrowingRepo;
import com.example.book_store.response.BorrowBookResponse;
import com.example.book_store.response.ReturnBookResponse;
import org.springframework.stereotype.Service;

@Service
public class BorrowingService {
    private final BorrowingRepo borrowingRepo;
    private final BookCopyService bookCopyService;
    private final UserAppService userAppService;

    public  BorrowingService(BorrowingRepo borrowingRepo,BookCopyService bookCopyService, UserAppService userAppService){
        this.borrowingRepo = borrowingRepo;
        this.bookCopyService = bookCopyService;
        this.userAppService = userAppService;
    }

    public BorrowBookResponse borrowBookCopy(BorrowBookInput input) {
        BookCopy bookCopy = bookCopyService.getBookCopyById(input.bookCopyId());
        if(bookCopy.getStatus().equals(Status.BORROWED)){
            throw new BookAlreadyBorrowedException("The give book is already borrowed");
        }
        UserApp userApp = userAppService.findByEmail(input.userEmail()).orElseThrow(
                ()-> new RecordNotFoundException(String.format("The given email: %s is not registered",input.userEmail()))
        );
        Borrowing borrowing = new Borrowing(null,bookCopy,userApp, BorrowingStatus.BORROWED);
        borrowing = borrowingRepo.save(borrowing);
        bookCopyService.saveAsBorrowed(bookCopy);
        BorrowBookResponse response = new BorrowBookResponse(borrowing.getBorrowingId(),userApp.getEmail(),bookCopy,borrowing.getStatus());
        return response;
    }

    public ReturnBookResponse returnBookCopy(ReturnBookInput input) {
        Borrowing borrowing = borrowingRepo.findById(input.borrowingId()).orElseThrow(
                ()-> new RecordNotFoundException(String.format("The given borrow id: %s is not found",input.borrowingId()))
        );
        borrowing.setStatus(BorrowingStatus.RETURNED);
        bookCopyService.saveAsReturned(borrowing.getBookCopy());
        ReturnBookResponse response = new ReturnBookResponse(borrowing.getBorrowingId(),borrowing.getUser().getEmail(),borrowing.getBookCopy(),borrowing.getStatus());
        return response;
    }
}
