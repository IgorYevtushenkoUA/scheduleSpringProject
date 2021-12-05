package com.example.faculty.database.repository;

import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where u.role='TEACHER'")
    List<User> getAllTeacher();

}
