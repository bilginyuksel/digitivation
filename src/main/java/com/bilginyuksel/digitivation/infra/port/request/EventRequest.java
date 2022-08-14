package com.bilginyuksel.digitivation.infra.port.request;

import com.bilginyuksel.digitivation.invitation.model.Event;

import java.time.LocalDateTime;

public record EventRequest(
        LocalDateTime time,
        String place,
        String customMessage
) {
    public Event toEvent() {
        return Event.create()
                .place(place)
                .time(time)
                .customMessage(customMessage)
                .build();
    }
}
