package com.example.faculty.services.interfaces;

import com.example.faculty.database.dto.user.UserCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.dto.user.UserUpdateDto;
import com.example.faculty.database.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<UserResponseDto> getAll();

    Optional<UserResponseDto> get(UUID id);

    void delete(UUID id);

    UserResponseDto create(UserCreateDto dto);

    UserResponseDto update(UserUpdateDto dto);

    User findByEmail(String email);
}
