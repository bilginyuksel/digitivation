package com.bilginyuksel.digitivation.infra.port.response;

import com.bilginyuksel.digitivation.invitation.model.InvitationFile;

public record InvitationFileResponse(
        String url,
        String filename,
        String contentType,
        long size
) {

    public static InvitationFileResponse from(InvitationFile invitationFile) {
        return new InvitationFileResponse(
                invitationFile.getUrl(),
                invitationFile.getFilename(),
                invitationFile.getContentType(),
                invitationFile.getSize()
        );
    }
}
