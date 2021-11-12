package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subject", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Subject {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "id cannot be null")
    private UUID id;

    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "speciality")
    private String speciality;

    @Min(1)
    @Max(4)
    @Column(name = "course")
    private int course;

    @Column(name = "code")
    private String code;

    @Min(1)
    @Max(3)
    @Column(name = "trim")
    private String trim;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<User> peopleInSubject;

    @OneToMany(mappedBy="subject")
    private List<Event> events;

}
