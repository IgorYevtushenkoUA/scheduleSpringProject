package com.example.faculty.controller;

import com.example.faculty.database.dto.subject.SubjectCreateDto;
import com.example.faculty.database.dto.subject.SubjectResponseDto;
import com.example.faculty.database.dto.subject.SubjectUpdateDto;
import com.example.faculty.services.interfaces.ISubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final ISubjectService service;

    public SubjectController(ISubjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubjectResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<SubjectResponseDto> get(@PathVariable UUID id) {
        return service.get(id);
    }

    @PostMapping("/create")
    public SubjectResponseDto create(@RequestBody SubjectCreateDto dto) {
        return service.create(dto);
    }

    @PutMapping("/edit")
    public SubjectResponseDto update(@RequestBody SubjectUpdateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
