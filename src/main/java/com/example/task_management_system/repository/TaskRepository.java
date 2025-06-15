package com.example.task_management_system.repository;

import com.example.task_management_system.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    // Using @Query annotation (most reliable)
    @Query("SELECT t FROM Task t WHERE t.user.email = :email ORDER BY t.creationTime DESC")
    List<Task> findTasksByUserEmailOrderByCreationTimeDesc(@Param("email") String email);

    // List<Task> findAllByOrderByCreationTimeDesc();

    @Query("SELECT t FROM Task t ORDER BY t.creationTime DESC")
    List<Task> findAllOrderByCreationTimeDesc();

        //find all tasks assigned to assignee with email

    @Query("SELECT t FROM Task t WHERE t.assignee.email = :email ORDER BY t.creationTime DESC")
    List<Task> findTasksByAssigneeEmailOrderByCreationTimeDesc(@Param("email") String email);


}