package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Role;
import com.example.faculty.database.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByName(UserRole name);
}