package com.example.keycloak.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(
        name = "EVENT_OUTBOX"
)
public class EventOutbox {

    @Id
    @Column(
            name = "ID")
    private Long id;
    @Column(
            name = "EVENT_TIME"
    )
    private long time;
    @Column(
            name = "EVENT_TYPE"
    )
    private String type;

    @Column(
            name = "EVENT_ID"
    )
    private String eventId;

    public EventOutbox() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

