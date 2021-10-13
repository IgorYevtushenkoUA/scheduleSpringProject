package com.example.faculty.database.entity;

import com.example.faculty.database.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private long created = new Date().getTime();

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "parental")
    private String parental;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Lob
    @Column(name = "about")
    private String about;

    @Column(name = "course")
    private int course;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "avatar_id")
    private long avatarId;

}
