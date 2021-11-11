package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id", updatable = false, nullable = false, unique = true)
    private Long eventId;

    @NotNull
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

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
    @Column(name = "request", nullable = false)
    private boolean isRequest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @Column
    private Subject subjectId;

    @OneToMany
    @JoinColumn(name = "request_id")
    @Transient
    private List<Request> requests;

}
