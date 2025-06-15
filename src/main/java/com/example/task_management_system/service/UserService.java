package com.example.task_management_system.service;

import com.example.task_management_system.dto.UserDTO;
import com.example.task_management_system.entities.User;
import com.example.task_management_system.mapper.ModelMapper;
import com.example.task_management_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;


    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public void saveUser(UserDTO userDTO)
    {
        userRepository.save(mapper.convertUserDTOToUser(userDTO));

    }



    public boolean existsByEmail(String email)
    {
        return userRepository.existsByEmailIgnoreCase(email);

    }

    //find user by email case-insensitive
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmailIgnoreCase(email).orElseThrow(()->new UsernameNotFoundException("User does not exist"));
    }



}
