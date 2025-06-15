package com.example.task_management_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String status="CREATED";

    private LocalDateTime creationTime=LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
  //  @JsonIgnoreProperties({"id","password"})
    private User user;


    //New task may or may not have an assignee so nullable=true
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="assignee")
    private User assignee;



    public Task(String title, String description,User user) {

        this.title = title;
        this.description = description;
        this.user = user;
       // this.assignee=assignee;
    }


    public Long getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }



    public LocalDateTime getCreationTime() {
        return creationTime;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Optional<User> getAssignee() {
        return Optional.ofNullable(user);
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }
}
