package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private List<Event> events;
}
