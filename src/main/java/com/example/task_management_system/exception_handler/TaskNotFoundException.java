package com.example.task_management_system.exception_handler;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String message)
    {
        super(message);
    }
}
