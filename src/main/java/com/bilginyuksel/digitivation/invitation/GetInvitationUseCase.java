package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.invitation.model.Invitation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetInvitationUseCase implements BusinessUseCase<String, Invitation> {
    private InvitationRepository invitationRepository;

    @Override
    public Invitation handle(String id) {
        return invitationRepository.find(id);
    }
}
