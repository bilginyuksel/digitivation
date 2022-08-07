package com.bilginyuksel.digitivation.port.request;

import com.bilginyuksel.digitivation.invitation.model.Invitation;

public record InvitationRequest(
        PersonRequest groom,
        PersonRequest bride,
        EventRequest marriage,
        EventRequest wedding,
        EventRequest hennaNight
) {
    public Invitation toInvitation() {
        return Invitation.create()
                .bride(bride.toPersonToMarry())
                .groom(groom.toPersonToMarry())
                .marriage(marriage.toEvent())
                .hennaNight(hennaNight.toEvent())
                .wedding(wedding.toEvent())
                .build();
    }
}
