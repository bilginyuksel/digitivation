package com.bilginyuksel.digitivation.infra.port.response;

import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.InvitationFile;
import lombok.Getter;

import java.util.List;

@Getter
public class InvitationResponse {
    private PersonResponse groom;
    private PersonResponse bride;
    private EventResponse marriage;
    private EventResponse wedding;
    private EventResponse hennaNight;
    private String status;
    private boolean isPaid;
    private List<String> files;

    public static InvitationResponse from(Invitation weddingInvitation) {
        var response = new InvitationResponse();
        response.bride = new PersonResponse(
                weddingInvitation.getBride().getName(),
                weddingInvitation.getBride().getSurname(),
                weddingInvitation.getBride().getFatherName(),
                weddingInvitation.getBride().getFatherSurname(),
                weddingInvitation.getBride().getMotherName(),
                weddingInvitation.getBride().getMotherSurname()
        );
        response.groom = new PersonResponse(
                weddingInvitation.getGroom().getName(),
                weddingInvitation.getGroom().getSurname(),
                weddingInvitation.getGroom().getFatherName(),
                weddingInvitation.getGroom().getFatherSurname(),
                weddingInvitation.getGroom().getMotherName(),
                weddingInvitation.getGroom().getMotherSurname()
        );

        if (weddingInvitation.getMarriage() != null) {
            response.marriage = new EventResponse(
                    weddingInvitation.getMarriage().getTime(),
                    weddingInvitation.getMarriage().getPlace(),
                    weddingInvitation.getMarriage().getCustomMessage()
            );
        }

        if (weddingInvitation.getWedding() != null) {
            response.wedding = new EventResponse(
                    weddingInvitation.getWedding().getTime(),
                    weddingInvitation.getWedding().getPlace(),
                    weddingInvitation.getWedding().getCustomMessage()
            );
        }

        if (weddingInvitation.getHennaNight() != null) {
            response.hennaNight = new EventResponse(
                    weddingInvitation.getHennaNight().getTime(),
                    weddingInvitation.getHennaNight().getPlace(),
                    weddingInvitation.getHennaNight().getCustomMessage()
            );
        }

        response.isPaid = weddingInvitation.isPaid();
        response.status = weddingInvitation.getStatus().name();

        if (weddingInvitation.getFiles() != null) {
            response.files = weddingInvitation.getFiles().stream().map(InvitationFile::getUrl).toList();
        }
        return response;
    }
}
