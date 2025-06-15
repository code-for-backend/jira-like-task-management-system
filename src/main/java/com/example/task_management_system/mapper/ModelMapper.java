package com.example.task_management_system.mapper;

import com.example.task_management_system.dto.TaskDTO;
import com.example.task_management_system.dto.TaskResponseDTO;
import com.example.task_management_system.dto.UserDTO;
import com.example.task_management_system.entities.Task;
import com.example.task_management_system.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    private final PasswordEncoder passwordEncoder;

    public ModelMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User convertUserDTOToUser(UserDTO userDTO)
    {
        String encodedPassword=passwordEncoder.encode(userDTO.getPassword());
        return new User(userDTO.getEmail(), encodedPassword);

    }


    public Task convertTaskDTOToTask(TaskDTO taskDTO,User user)
    {
        return new Task(taskDTO.getTitle(),taskDTO.getDescription(),user);
    }

    public TaskResponseDTO convertTaskToDTO(Task task)
    {
        return new TaskResponseDTO(task.getId(),task.getTitle(),task.getDescription(),task.getStatus()
                ,task.getUser().getEmail(),task.getAssignee().map(user->user.getEmail()).orElse("none"));
    }


}
