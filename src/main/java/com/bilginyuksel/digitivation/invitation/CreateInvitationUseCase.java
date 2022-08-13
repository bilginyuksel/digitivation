package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.invitation.configuration.InvitationConfiguration;
import com.bilginyuksel.digitivation.invitation.model.Invitation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateInvitationUseCase implements BusinessUseCase<Invitation, String> {
    private InvitationRepository invitationRepository;
    private InvitationConfiguration invitationConfiguration;

    @Override
    public String handle(Invitation invitation) {
        calculatePrice(invitation);

        return invitationRepository.save(invitation);
    }

    private void calculatePrice(Invitation invitation) {
        var price = invitationConfiguration.getBasePrice();
        var discount = price * invitationConfiguration.getDiscount();
        var paidPrice = invitationConfiguration.getBasePrice() - discount;

        invitation.setPrice(price);
        invitation.setPaidPrice(paidPrice);
    }
}
