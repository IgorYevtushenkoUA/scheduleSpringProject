package com.example.faculty.controller;

import com.example.faculty.services.interfaces.IRequestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    private final IRequestService service;

    public RequestController(IRequestService service) {
        this.service = service;
    }
}
