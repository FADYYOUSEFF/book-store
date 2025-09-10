package com.example.book_store.service;

import com.example.book_store.entity.UserApp;
import com.example.book_store.entity.UserPrinciple;
import com.example.book_store.exception.EmailIsAlreadyUsedException;
import com.example.book_store.mapper.UserMapper;
import com.example.book_store.payload.RegisterInput;
import com.example.book_store.repository.UserAppRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserAppService implements UserDetailsService {

    private final UserAppRepo userAppRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    UserAppService(UserAppRepo userAppRepo,UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userAppRepo=userAppRepo;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;
    }

    public UserApp saveNewUser(RegisterInput registerInput) {
        UserApp userApp = userMapper.fromRegisterInput(registerInput);
        if(findByEmail(userApp.getEmail()).isPresent()){
            throw new EmailIsAlreadyUsedException("The give email is already used");
        }
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return userAppRepo.save(userApp);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userAppRepo.findByEmail(username).orElseThrow(
                ()-> new EmailIsAlreadyUsedException("The give email is already used")
        );
        return new UserPrinciple(userApp);
    }

    public Optional<UserApp> findByEmail(String email) {
        return userAppRepo.findByEmail(email);
    }
}
