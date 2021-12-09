package com.example.faculty.database.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Type(type = "uuid-char")
    @NotNull
    private UUID id;

    @CreatedDate
    @Column(name = "created_at")
    @NotNull
    private long created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    @NotNull
    private long updated_at;
}
