package com.bilginyuksel.digitivation.invitation.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class UploadInvitationFiles {
    private String invitationId;
    private List<InvitationFile> files;
}
