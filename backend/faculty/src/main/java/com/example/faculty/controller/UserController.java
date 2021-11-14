package com.example.faculty.controller;

import com.example.faculty.database.dto.user.UserCreateDto;
import com.example.faculty.database.dto.user.UserResponseDto;
import com.example.faculty.database.dto.user.UserUpdateDto;
import com.example.faculty.services.interfaces.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<UserResponseDto> get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping("/create")
    public UserResponseDto create(@RequestBody UserCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/edit")
    public UserResponseDto update(@RequestBody UserUpdateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
