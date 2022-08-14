package com.bilginyuksel.digitivation.infra.port.request;

import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.Status;

public record InvitationRequest(
        PersonRequest groom,
        PersonRequest bride,
        EventRequest marriage,
        EventRequest wedding,
        EventRequest hennaNight
) {
    public Invitation toInvitation() {
        return Invitation.create()
                .paid(false)
                .status(Status.PENDING_PAYMENT)
                .bride(bride.toPersonToMarry())
                .groom(groom.toPersonToMarry())
                .marriage(marriage.toEvent())
                .hennaNight(hennaNight.toEvent())
                .wedding(wedding.toEvent())
                .build();
    }
}
