package com.example.services;

import com.example.dtos.SignupRequest;
import com.example.dtos.UserDto;
import com.example.models.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User(signupRequest.getName(), signupRequest.getUsername(), signupRequest.getEmail(), new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);

        return new UserDto(createdUser.getName(), createdUser.getUsername(), createdUser.getEmail(), new BCryptPasswordEncoder().encode(createdUser.getPassword()));
    }
}
