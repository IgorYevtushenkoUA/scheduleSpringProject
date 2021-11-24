package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.EventData;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "event")
@Getter
@Setter
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Type(type = "uuid-char")
    private UUID id;

    @CreatedDate
    @Column(name="created_at")
    private long created_at;

    @LastModifiedDate
    @Column(name="updated_at")
    private long updated_at;

    @NotNull
    @Column(name = "group_name")
    private String group;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "auditory")
    private String auditory;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;


    @Column(name = "datetime")
    private Timestamp datetime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "event")
    @ToString.Exclude
    List<Request> requests;

    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Attendee> attendees;
}
