package com.example.faculty.services.implementations;

import com.example.faculty.database.dto.request.RequestCreateDto;
import com.example.faculty.database.dto.request.RequestResponseDto;
import com.example.faculty.database.dto.request.RequestUpdateDto;
import com.example.faculty.database.entity.Request;
import com.example.faculty.database.mapstruct.mappers.IRequestMapper;
import com.example.faculty.database.repository.RequestRepository;
import com.example.faculty.services.interfaces.IRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements IRequestService {
    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RequestResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(IRequestMapper.MAPPER::requestToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RequestResponseDto> get(UUID id) {
        return repository.findById(id)
                .map(IRequestMapper.MAPPER::requestToResponse);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public RequestResponseDto create(RequestCreateDto dto) {
        Request request = IRequestMapper.MAPPER.createToRequest(dto);
        return IRequestMapper.MAPPER.requestToResponse(repository.save(request));
    }

    @Override
    public RequestResponseDto update(RequestUpdateDto dto) {
        Request request = IRequestMapper.MAPPER.updateToRequest(dto);
        return IRequestMapper.MAPPER.requestToResponse(repository.save(request));
    }

    @Override
    public void deleteByEvent(UUID id) {
        repository.deleteByEvent(id);
    }

}
