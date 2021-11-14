package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import com.example.faculty.database.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column
    private String parental;

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

    @Column(nullable = false)
    private long avatarId;

    @OneToMany(mappedBy = "user")
    List<Attendee> attending;

    @OneToMany(mappedBy = "creator")
    List<Request> requests;

    @OneToMany(mappedBy = "creator")
    List<Event> created;
}
