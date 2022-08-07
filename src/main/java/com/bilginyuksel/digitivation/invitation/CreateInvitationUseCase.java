package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateInvitationUseCase implements BusinessUseCase<Invitation, Invitation> {
    private InvitationRepository invitationRepository;

    @Override
    public Invitation handle(Invitation invitation) {
        invitation.setStatus(Status.PENDING_PAYMENT);
        invitation.setPaid(false);

        var id = invitationRepository.save(invitation);
        invitation.setId(id);

        return invitation;
    }
}
