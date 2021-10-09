package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.models.requests.UserRequest;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User createUser(UserRequest userRequest);

    User updateUser(Long userId, UserRequest userRequest);

    User getUserById(Long userId);

    void deleteUser(Long userId);

    List<User> getAllUsersByRole(UserRole role);

    Page<User> getAllUsersByRole(UserRole role, Pageable pageable);

    List<User> getAllUsersByCourse(String course);

    Page<User> getAllUsersByCourse(String course, Pageable pageable);

    List<User> getAllUsersByFaculty(String faculty);

    Page<User> getAllUsersByFaculty(String faculty, Pageable pageable);

}
