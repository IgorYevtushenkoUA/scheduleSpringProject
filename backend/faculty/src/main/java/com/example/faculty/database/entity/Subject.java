package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "faculty")
    @NotNull
    private String faculty;

    @Column(name = "speciality")
    @NotNull
    private String speciality;

    @Column(name = "course")
    @Min(1)
    @Max(4)
    @NotNull
    private int course;

    @Column(name = "code")
    @NotNull
    private int code;

    @Column(name = "trim")
    @NotNull
    private String trim;

//    @CreatedDate
//    @Column(name = "created")
//    private long created = new Date().getTime();

}
