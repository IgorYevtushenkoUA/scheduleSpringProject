package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.EventData;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "event")
public class Event extends EventData {
    @Column
    private Timestamp datetime;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "event")
    List<Request> requests;

    @OneToMany(mappedBy = "event")
    List<Attendee> attendees;
}
