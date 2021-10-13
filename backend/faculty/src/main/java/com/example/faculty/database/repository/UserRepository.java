package com.example.faculty.database.repository;

import com.example.faculty.database.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
