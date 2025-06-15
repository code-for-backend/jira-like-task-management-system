package com.example.task_management_system.exception_handler;

public class TaskAccessDeniedException extends RuntimeException{

    public TaskAccessDeniedException(String message)
    {
        super(message);
    }
}
