package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "subject")
public class Subject extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String faculty;

    @Column
    private String speciality;

    @Column
    @Min(1)
    @Max(4)
    private int course;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String trim;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Event> events;
}
