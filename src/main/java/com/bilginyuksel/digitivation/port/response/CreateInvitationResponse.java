package com.bilginyuksel.digitivation.port.response;

public record CreateInvitationResponse(String id) {

    public static CreateInvitationResponse from(String id) {
        return new CreateInvitationResponse(id);
    }
}
