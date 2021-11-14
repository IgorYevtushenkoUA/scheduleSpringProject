package com.example.faculty.services.interfaces;

import com.example.faculty.database.entity.User;
import com.example.faculty.services.interfaces.base.IBaseService;

import java.util.UUID;

public interface IUserService extends IBaseService<User, UUID> {
}
