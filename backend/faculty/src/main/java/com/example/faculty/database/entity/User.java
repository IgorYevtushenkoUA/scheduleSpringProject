package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import com.example.faculty.database.enums.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String surname;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column(unique = true)
    @NotBlank
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column
    private String parental;

    @Lob
    @Column
    private String about;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private UserRole role;

    public User(String username, String email, String password, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = UserRole.STUDENT;
    }

    public User(String name, String surname, String parental, String email, String password,
                String username, String about, int course, String faculty, UserRole role) {
        this.name = name;
        this.surname = surname;
        this.parental = parental;
        this.email = email;
        this.password = password;
        this.username = username;
        this.about = about;
        this.course = course;
        this.faculty = faculty;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}