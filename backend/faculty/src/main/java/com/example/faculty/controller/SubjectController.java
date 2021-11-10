package com.example.faculty.controller;

import com.example.faculty.database.entity.Subject;
import com.example.faculty.services.interfaces.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("{id}")
    public Subject get(@PathVariable Long id) {
        return subjectService.getSubjectById(id).orElse(null);
    }
}
