package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.User;
import com.example.faculty.database.enums.UserRole;
import com.example.faculty.database.repository.UserRepository;
import com.example.faculty.models.requests.UserRequest;
import com.example.faculty.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public User updateUser(Long userId, UserRequest userRequest) {
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
    }

    @Override
    public List<User> getAllUsersByRole(UserRole role) {
        return null;
    }

    @Override
    public Page<User> getAllUsersByRole(UserRole role, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> getAllUsersByCourse(String course) {
        return null;
    }

    @Override
    public Page<User> getAllUsersByCourse(String course, Pageable pageable) {
        return null;
    }

    @Override
    public List<User> getAllUsersByFaculty(String faculty) {
        return null;
    }

    @Override
    public Page<User> getAllUsersByFaculty(String faculty, Pageable pageable) {
        return null;
    }
}
