package com.example.task_management_system.service;

import com.example.task_management_system.dto.TaskAssigneeDTO;
import com.example.task_management_system.dto.TaskDTO;
import com.example.task_management_system.dto.TaskResponseDTO;
import com.example.task_management_system.dto.TaskStatusDTO;
import com.example.task_management_system.entities.Task;
import com.example.task_management_system.entities.User;
import com.example.task_management_system.exception_handler.TaskNotFoundException;
import com.example.task_management_system.exception_handler.UsernameNotFoundException;
import com.example.task_management_system.mapper.ModelMapper;
import com.example.task_management_system.repository.TaskRepository;
import com.example.task_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    public void saveTask(TaskDTO taskDTO, User author)
    {
        Task task= modelMapper.convertTaskDTOToTask(taskDTO,author);
        taskRepository.save(task); //create a new task

    }


    public List<TaskResponseDTO> findTasks(String author)
    {

        List<Task> tasks=(author==null)?taskRepository.findAllOrderByCreationTimeDesc():
                taskRepository.findTasksByUserEmailOrderByCreationTimeDesc(author);

        return tasks.stream().map(task->modelMapper.convertTaskToDTO(task)).collect(Collectors.toList());


    }


    public TaskResponseDTO updateTaskStatus(TaskStatusDTO taskStatusDTO,long id)
    {
        Task task=taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException("Task with id not found"));
        task.setStatus(taskStatusDTO.toString());
        return modelMapper.convertTaskToDTO(taskRepository.save(task));

    }


    public TaskResponseDTO assignTask(TaskAssigneeDTO taskAssigneeDTO,long id)
    {
        User assignee=userRepository.findUserByEmailIgnoreCase(taskAssigneeDTO.getAssignee())
                .orElseThrow(()->new UsernameNotFoundException("The assignee does not exists"));

        Task task=taskRepository.findById(id).
                orElseThrow(()->new TaskNotFoundException("Task with id "+id+" does not exists"));

        task.setAssignee(assignee);

        Task savedTask=taskRepository.save(task);

        return modelMapper.convertTaskToDTO(savedTask);
    }
}

