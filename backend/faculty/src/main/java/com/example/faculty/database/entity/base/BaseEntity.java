package com.example.faculty.database.entity.base;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @Type(type="uuid-char")
    private UUID id;

    @CreatedDate
    @Column
    private long created_at;

    @LastModifiedDate
    private long updated_at;
}
