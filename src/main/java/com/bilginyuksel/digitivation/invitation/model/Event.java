package com.bilginyuksel.digitivation.invitation.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class Event {
    private LocalDateTime time;
    private String place;
    private String customMessage;
}
