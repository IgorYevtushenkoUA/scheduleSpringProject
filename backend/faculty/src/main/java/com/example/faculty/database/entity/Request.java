package com.example.faculty.database.entity;

import com.example.faculty.database.entity.base.EventData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "request")
public class Request extends EventData {
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User creator;

    @ManyToOne
    @JoinColumn(name="event_id", nullable=false)
    private Event event;
}
