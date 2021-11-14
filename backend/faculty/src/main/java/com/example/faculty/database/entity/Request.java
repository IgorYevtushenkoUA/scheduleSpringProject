package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.EventData;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "request")
public class Request extends EventData {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
