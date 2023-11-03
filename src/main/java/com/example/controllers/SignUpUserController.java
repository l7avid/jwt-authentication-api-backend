package com.example.controllers;

import com.example.dtos.Message;
import com.example.dtos.SignupRequest;
import com.example.dtos.UserDto;
import com.example.enums.RoleName;
import com.example.models.Role;
import com.example.models.User;
import com.example.services.AuthService;
import com.example.services.RoleService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SignUpUserController {

    @Autowired
    private AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Message("Wrong fields or invalid email"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUsername(signupRequest.getUsername())){
            return new ResponseEntity(new Message("Username already exists"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByEmail(signupRequest.getEmail())){
            return new ResponseEntity(new Message("Existing account with this email"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(signupRequest.getName(), signupRequest.getUsername(), signupRequest.getEmail(), new BCryptPasswordEncoder().encode(signupRequest.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleName.ROLE_USER).get());

        if(signupRequest.getRoles().contains("admin")){
            roles.add(roleService.getByRoleName(RoleName.ROLE_ADMIN).get());
        }

        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity(new Message("User saved successfully"), HttpStatus.CREATED);
    }
}
