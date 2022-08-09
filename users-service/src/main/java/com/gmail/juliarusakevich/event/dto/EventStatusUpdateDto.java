package com.gmail.juliarusakevich.event.dto;

import com.gmail.juliarusakevich.event.repository.entity.enums.EventStatus;

public class EventStatusUpdateDto {

    EventStatus status;

    public EventStatusUpdateDto() {
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }
}
