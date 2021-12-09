package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "subject")
public class Subject extends BaseEntity {

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String faculty;

    @Column
    private String speciality;

    @Column
    @Min(1)
    @Max(6)
    private int course;

    @Column
    @NonNull
    private int code;

    @Column
    @NotBlank
    private String trim;

    @OneToMany(mappedBy = "subject")
    @ToString.Exclude
    private List<Event> events;
}
