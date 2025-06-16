package com.example.task_management_system.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TaskAssigneeDTO {
    @NotBlank
    @Valid
    @Email
    private String assignee;


    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public TaskAssigneeDTO(String assignee) {
        this.assignee = assignee;
    }
}
