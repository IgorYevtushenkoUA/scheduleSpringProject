package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Request;
import com.example.faculty.database.repository.RequestRepository;
import com.example.faculty.services.interfaces.IRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestServiceImpl implements IRequestService {
    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }
}
