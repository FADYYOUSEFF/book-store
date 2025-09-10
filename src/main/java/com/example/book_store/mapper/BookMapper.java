package com.example.book_store.mapper;

import com.example.book_store.entity.Book;
import com.example.book_store.payload.SaveBookInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book fromSaveBookInput(SaveBookInput input);
}
