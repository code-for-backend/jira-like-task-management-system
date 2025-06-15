package com.example.task_management_system.controller;

import com.example.task_management_system.dto.UserDTO;
import com.example.task_management_system.entities.User;
import com.example.task_management_system.exception_handler.EmailNotUniqueException;
import com.example.task_management_system.service.UserInfoService;
import com.example.task_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/accounts")
    public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO)
    {
        //Checks if user with the email already exists
        if(userService.existsByEmail(userDTO.getEmail()))
            throw new EmailNotUniqueException("Email already exists!");

        //save the user
        userService.saveUser(userDTO);
        return ResponseEntity.status(200).body("Account created");

    }


}
