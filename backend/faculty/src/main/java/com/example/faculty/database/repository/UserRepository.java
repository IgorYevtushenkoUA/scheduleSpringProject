package com.example.faculty.database.repository;

import com.example.faculty.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM USERS u\n" +
            "left join user_roles ur on u.id=ur.user_id\n" +
            "left join roles r on r.id=ur.role_id\n" +
            " WHERE r.name='TEACHER';", nativeQuery = true)
    //@Query("select u from User u where u.role   'TEACHER'")
    List<User> getAllTeachers();

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    User findByEmail(String email);
}
