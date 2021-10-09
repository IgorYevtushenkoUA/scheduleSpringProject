package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.Request;
import com.example.faculty.database.repository.RequestRepository;
import com.example.faculty.models.requests.RequestRequest;
import com.example.faculty.services.interfaces.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    public RequestServiceImpl() {

    }

    @Override
    public Request createRequest(RequestRequest request) {
        return null;
    }

    @Override
    public Request updateRequest(Long requestId, RequestRequest request) {
        return null;
    }

    @Override
    public Request getRequestById(Long requestId) {
        return null;
    }

    @Override
    public void deleteRequest(Long requestId) {

    }

    @Override
    public List<Request> getAllRequestsByUser(Long userId) {
        return null;
    }

    @Override
    public Page<Request> getAllRequests(Pageable pageable) {
        return null;
    }
}
