package com.bilginyuksel.digitivation.port.request;

public record WeddingInvitationRequest(
        PersonRequest groom,
        PersonRequest bride,
        EventRequest marriage,
        EventRequest wedding,
        EventRequest hennaNight
) {
}
