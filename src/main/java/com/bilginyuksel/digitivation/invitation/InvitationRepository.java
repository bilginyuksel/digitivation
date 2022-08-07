package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.invitation.model.Invitation;

public interface InvitationRepository {
    String save(Invitation i);

    Invitation find(String id);
}
