package com.bilginyuksel.digitivation.infra.port.request;

public record WeddingInvitationRequest(
        PersonRequest groom,
        PersonRequest bride,
        EventRequest marriage,
        EventRequest wedding,
        EventRequest hennaNight
) {
}
