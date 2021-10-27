package com.example.faculty.database.entity;

import com.example.faculty.database.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

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
    @Column(name = "course", nullable = true)
    private int course;

    @Column(name = "faculty", nullable = true)
    private String faculty;

    @NotNull
    @Column(name = "avatar_id")
    private long avatarId;

//    @NotNull
//    @CreatedDate
//    @Column(name = "created")
//    private long created = new Date().getTime();

}
