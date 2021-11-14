package com.example.faculty.services.implementations;

import com.example.faculty.database.entity.User;
import com.example.faculty.database.repository.UserRepository;
import com.example.faculty.services.implementations.base.BaseServiceImpl;
import com.example.faculty.services.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, UUID> implements IUserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
