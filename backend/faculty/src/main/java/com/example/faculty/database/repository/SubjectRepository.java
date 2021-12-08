package com.example.faculty.database.repository;

import com.example.faculty.database.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {

    @Query("select s from Subject s where lower(s.name) like lower(concat('%', :name ,'%') )")
    List<Subject> findByName(@Param("name") String name);

}
