package com.example.faculty.services.implementations;

import com.example.faculty.database.repository.RequestRepository;
import com.example.faculty.services.interfaces.IRequestService;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements IRequestService {
    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }
}
