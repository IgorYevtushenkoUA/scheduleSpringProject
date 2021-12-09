package com.example.faculty.controller;

import com.example.faculty.services.interfaces.IRequestService;
import com.example.faculty.util.annotations.LogInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/request")
@LogInfo
@CacheConfig(cacheNames = {"requests"})
public class RequestController {
    private final IRequestService service;

    public RequestController(IRequestService service) {
        this.service = service;
    }
}
