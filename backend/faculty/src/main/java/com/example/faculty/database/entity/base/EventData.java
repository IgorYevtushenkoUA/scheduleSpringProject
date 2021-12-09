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
import javax.validation.constraints.NotBlank;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventData extends BaseEntity {
    @NotBlank
    @Column(name = "group_name")
    private String group;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "auditory")
    private String auditory;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
