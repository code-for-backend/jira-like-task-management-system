package com.example.task_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is require")
    @Size(message = "Password must be atleast 10 char long",min=10)
    private String password;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
