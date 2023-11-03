package com.example.services;

import com.example.dtos.SignupRequest;
import com.example.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
