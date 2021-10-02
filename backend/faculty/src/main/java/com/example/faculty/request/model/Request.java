package com.example.faculty.request.model;

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
@Table(name = "request")
public class Request extends BaseEntity {

    @Column(name = "user_id")
    private long userId;

    @Column(name = "event_id")
    private long eventId;

}
