package com.example.task_management_system.service;

import com.example.task_management_system.adapter.UserAdapter;
import com.example.task_management_system.entities.User;
import com.example.task_management_system.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserInfoService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findUserByEmailIgnoreCase(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new UserAdapter(user);
    }


}
