package com.example.task_management_system.controller;

import com.example.task_management_system.dto.TaskAssigneeDTO;
import com.example.task_management_system.dto.TaskDTO;
import com.example.task_management_system.dto.TaskResponseDTO;
import com.example.task_management_system.dto.TaskStatusDTO;
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
import org.springframework.web.bind.annotation.*;

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

    //update the status of the task

    /*

   Note in postman directly send the enum as request body

   e.g  "COMPLETED" or "IN_PROGRESS"
   makes sense since it's an enum

   Or we can use a wrapper to wrap the enum value.

   The first option was chosen here
     */

@PutMapping("/api/tasks/{taskId}/status")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@Valid @RequestBody TaskStatusDTO taskStatusDTO,@PathVariable long taskId)
    {
        System.out.println("Task id is "+taskId);
        TaskResponseDTO updatedTask=taskService.updateTaskStatus(taskStatusDTO,taskId);

        return ResponseEntity.status(200).body(updatedTask);

    }

    //each user can be assigned many tasks but each task must be assigned to one one and only one user
    //assign the task to a user
    @PutMapping("/api/tasks/{taskId}/assign")
    public ResponseEntity<TaskResponseDTO> updateAssignee(@Valid @RequestBody TaskAssigneeDTO taskAssigneeDTO,@PathVariable long taskId)
    {
        TaskResponseDTO taskResponseDTO=taskService.assignTask(taskAssigneeDTO,taskId);

        return ResponseEntity.status(200).body(taskResponseDTO);


    }

}
