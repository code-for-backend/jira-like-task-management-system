package com.example.task_management_system.controller;

import com.example.task_management_system.dto.TaskDTO;
import com.example.task_management_system.dto.TaskResponseDTO;
import com.example.task_management_system.entities.Task;
import com.example.task_management_system.entities.User;
import com.example.task_management_system.service.TaskService;
import com.example.task_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
private final TaskService taskService;
private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    //Create a new task
    @PostMapping("/api/tasks")
    public ResponseEntity<String> task(@Valid @RequestBody TaskDTO taskDTO, Authentication authentication)
    {
        UserDetails userDetails=(UserDetails)authentication.getPrincipal();
        String email=userDetails.getUsername();

        User author=userService.findUserByEmail(email);
        taskService.saveTask(taskDTO,author);
        return ResponseEntity.status(201).body("Task created");


    }

//Get list of tasks sorted by newest first ,optionally takes an author to get the list of tasks by author
    @GetMapping("/api/tasks")
    public ResponseEntity<List<TaskResponseDTO>> tasks(@RequestParam(required = false) String author)
    {

        List<TaskResponseDTO> tasks=taskService.findTasks(author);
        return ResponseEntity.status(200).body(tasks);
    }
}
