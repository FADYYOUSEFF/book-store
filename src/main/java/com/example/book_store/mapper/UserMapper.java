package com.example.book_store.mapper;

import com.example.book_store.entity.UserApp;
import com.example.book_store.payload.LoginInput;
import com.example.book_store.payload.RegisterInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserApp fromRegisterInput(RegisterInput registerInput);
    UserApp fromLoginInput(LoginInput loginInput);
}
