package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull(message = "id cannot be null")
    private UUID id;

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

    @Column(name = "user_id")
    private String organizer;

    @ManyToOne
    @JoinColumn(name="id", nullable=false)
    private Subject subject;

    @OneToMany
    @JoinColumn(name = "request_id")
    @Transient
    private List<Request> requests;

    @OneToOne(mappedBy = "event")
    private User user;

}
