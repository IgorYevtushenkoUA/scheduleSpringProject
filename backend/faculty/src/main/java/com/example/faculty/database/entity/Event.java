package com.example.faculty.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private long created = new Date().getTime();

    @Column(name = "group")
    private String group;

    @Column(name = "name")
    private String name;

    @Column(name = "auditory")
    private String auditory;

    @Column(name = "request")
    private boolean isRequest;

}
