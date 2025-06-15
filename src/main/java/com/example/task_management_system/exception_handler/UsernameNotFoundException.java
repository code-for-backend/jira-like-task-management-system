package com.example.task_management_system.exception_handler;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String message)
    {
        super(message);
    }

}
