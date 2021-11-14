package com.example.faculty.controller;

import com.example.faculty.services.interfaces.ISubjectService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final ISubjectService service;

    public SubjectController(ISubjectService service) {
        this.service = service;
    }
}
