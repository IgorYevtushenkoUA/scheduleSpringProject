package com.example.faculty.event.model;

import com.example.faculty.db.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "event")
public class Event extends BaseEntity {

    @Column(name = "group")
    private String group;

    @Column(name = "name")
    private String name;

    @Column(name = "auditory")
    private String auditory;

    @Column(name = "request")
    private boolean isRequest;

}
