package com.example.task_management_system.exception_handler;

public class EmailNotUniqueException extends RuntimeException{

    public EmailNotUniqueException(String message)
    {
        super(message);
    }

}
