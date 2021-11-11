package com.example.faculty.database.entity;

import com.example.faculty.database.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false, unique = true)
    private Long userId;

    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Email
    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "parental")
    private String parental;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Lob
    @Column(name = "about")
    private String about;

    @Min(1)
    @Max(4)
    @NotEmpty
    @Column(name = "course")
    private int course;

    @NotNull
    @Column(name = "faculty")
    private String faculty;

    @NotNull
    @Column(name = "avatar_id")
    private long avatarId;

    @ManyToMany(mappedBy = "peopleInSubject",fetch = FetchType.EAGER)
    private List<Subject> userSubjects;

    @OneToMany
    @JoinColumn(name = "event_id")
    @Transient
    private List<Event> events;

}
