package com.example.task_management_system.repository;

import com.example.task_management_system.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {



    //find email:-perform case-insensitive match
    Optional<User> findUserByEmailIgnoreCase(String email);


    public boolean existsByEmailIgnoreCase(String email);



}
