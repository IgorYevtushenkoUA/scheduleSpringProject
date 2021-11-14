package com.example.faculty.services.interfaces.base;

import java.util.List;
import java.util.Optional;

public interface IBaseService<T, Id> {
    List<T> getAll();

    Optional<T> get(Id id);

    void delete(Id id);
}
