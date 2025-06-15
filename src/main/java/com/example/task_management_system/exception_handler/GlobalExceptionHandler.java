package com.example.task_management_system.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException e)
    {
        Map<String,String> errors=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(TaskAccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(TaskAccessDeniedException e)
    {
        System.out.println("Access denied 403");
        return ResponseEntity.status(403).body(e.getMessage());
    }



@ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        return ResponseEntity.status(404).body(e.getMessage());

    }


    @ExceptionHandler(EmailNotUniqueException.class)
    public ResponseEntity<String> handleEmailNotUniqueException(EmailNotUniqueException e)
    {
        return ResponseEntity.status(409).body(e.getMessage());
    }


    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e)
    {
        return ResponseEntity.status(404).body(e.getMessage());
    }

}
