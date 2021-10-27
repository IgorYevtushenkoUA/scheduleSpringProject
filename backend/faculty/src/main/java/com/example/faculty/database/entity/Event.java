package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name="organizer")
    private long organizer;

    @NotNull
    @Column(name="subject_id")
    private long subjectId;

    @Column(name="datetime")
    private Timestamp timestamp;

    @NotNull
    @Column(name = "groups")
    private String group;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "auditory")
    private String auditory;

    @NotNull
    @Column(name = "request")
    private boolean isRequest;

//    @NotNull
//    @CreatedDate
//    @Column(name = "created")
//    private long created = new Date().getTime();

}
