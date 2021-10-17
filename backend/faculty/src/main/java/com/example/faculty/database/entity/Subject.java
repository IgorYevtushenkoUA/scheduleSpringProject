package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "course")
    private int course;

    @Column(name = "code")
    private int code;

    @Column(name = "trim")
    private String trim;

    @CreatedDate
    @Column(name = "created")
    private long created = new Date().getTime();

}
