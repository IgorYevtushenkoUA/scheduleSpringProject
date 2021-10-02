package com.example.faculty.user.model;

import com.example.faculty.db.enums.UserRole;
import com.example.faculty.db.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity {

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
