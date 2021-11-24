package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Table(name = "subject")
@Getter
@Setter
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name="created_at")
    private long created_at;

    @Column(name="updated_at")
    private long updated_at;

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
