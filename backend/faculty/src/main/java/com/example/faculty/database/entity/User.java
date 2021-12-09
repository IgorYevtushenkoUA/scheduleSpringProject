package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends BaseEntity {

    @Column(nullable = false)
//    @Column
    private String name;

    @Column(nullable = false)
//    @Column
    private String surname;

    @Column
    private String parental;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String username;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name, String surname, String parental, String email, String password,
                String username, String about, int course, String faculty, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.parental = parental;
        this.email = email;
        this.password = password;
        this.username = username;
        this.about = about;
        this.course = course;
        this.faculty = faculty;
        this.roles = roles;
    }

    public User(String name, String surname, String parental, String email,
                String about, int course, String faculty) {
        this.name = name;
        this.surname = surname;
        this.parental = parental;
        this.email = email;
        this.about = about;
        this.course = course;
        this.faculty = faculty;
    }

    public User(UUID id, String name, String surname, String parental, String email,
                String about, int course, String faculty) {
        super(id, 0, 0);
        this.name = name;
        this.surname = surname;
        this.parental = parental;
        this.email = email;
        this.about = about;
        this.course = course;
        this.faculty = faculty;
    }
}