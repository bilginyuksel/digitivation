package com.bilginyuksel.digitivation.invitation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class InvitationFile {
    private String url;
    private String filename;
    private String contentType;
    private long size;
    private byte[] content;
}
