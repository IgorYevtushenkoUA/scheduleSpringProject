package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.Request;
import com.example.faculty.models.requests.RequestRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestService {

    Request createRequest(RequestRequest request);

    Request updateRequest(Long requestId, RequestRequest request);

    Request getRequestById(Long requestId);

    void deleteRequest(Long requestId);

    List<Request> getAllRequestsByUser(Long userId);

    Page<Request> getAllRequests(Pageable pageable);
}
