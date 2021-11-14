package com.example.faculty.services.implementations.base;

import com.example.faculty.services.interfaces.base.IBaseService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BaseServiceImpl<T, Id> implements IBaseService<T, Id> {
    private JpaRepository<T, Id> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> get(Id id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Id id) {
        repository.deleteById(id);
    }
}
