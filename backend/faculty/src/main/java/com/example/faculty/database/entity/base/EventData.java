package com.example.faculty.database.entity.base;

import com.example.faculty.database.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventData extends BaseEntity {
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
}
