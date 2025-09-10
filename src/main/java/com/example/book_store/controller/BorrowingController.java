package com.example.book_store.controller;

import com.example.book_store.payload.BorrowBookInput;
import com.example.book_store.payload.ReturnBookInput;
import com.example.book_store.response.BorrowBookResponse;
import com.example.book_store.response.ReturnBookResponse;
import com.example.book_store.service.BorrowingService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
public class BorrowingController {

    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService){
        this.borrowingService = borrowingService;
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @MutationMapping
    public BorrowBookResponse borrowBookCopy(@Argument @Validated BorrowBookInput input){
        return borrowingService.borrowBookCopy(input);
    }
    @MutationMapping("hasAuthority('STAFF')")
    public ReturnBookResponse returnBookCopy(@Argument @Validated ReturnBookInput input){
        return borrowingService.returnBookCopy(input);
    }


}
