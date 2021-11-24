package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import com.example.faculty.database.enums.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private String parental;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Lob
    @Column
    private String about;

    @Min(1)
    @Max(4)
    @Column
    private int course;

    @Column
    private String faculty;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Attendee> attending;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Request> requests;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<Event> created;
}
