package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.invitation.model.InvitationFile;

public interface InvitationFileStorage {
    InvitationFile upload(InvitationFile file);
}
