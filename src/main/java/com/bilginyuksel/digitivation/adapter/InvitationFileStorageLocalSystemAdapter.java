package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.invitation.InvitationFileStorage;
import com.bilginyuksel.digitivation.invitation.model.InvitationFile;
import org.springframework.stereotype.Component;

@Component
public class InvitationFileStorageLocalSystemAdapter implements InvitationFileStorage {
    @Override
    public InvitationFile upload(InvitationFile file) {
        return null;
    }
}
