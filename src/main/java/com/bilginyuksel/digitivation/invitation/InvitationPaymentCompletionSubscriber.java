package com.bilginyuksel.digitivation.invitation;

import com.bilginyuksel.digitivation.invitation.model.Invitation;
import com.bilginyuksel.digitivation.invitation.model.Status;
import com.bilginyuksel.digitivation.payment.PaymentEmailSender;
import com.bilginyuksel.digitivation.payment.ReceiveThreeDSecurePaymentUseCase;
import com.bilginyuksel.digitivation.payment.event.PaymentCompletionEvent;
import com.bilginyuksel.digitivation.pubsub.Broker;
import com.bilginyuksel.digitivation.pubsub.Subscriber;
import org.springframework.stereotype.Service;

@Service
public class InvitationPaymentCompletionSubscriber implements Subscriber<PaymentCompletionEvent> {
    private final InvitationRepository repository;
    private final PaymentEmailSender paymentEmailSender;

    public InvitationPaymentCompletionSubscriber(InvitationRepository repository, PaymentEmailSender paymentEmailSender, Broker broker) {
        this.repository = repository;
        this.paymentEmailSender = paymentEmailSender;

        broker.subscribe(ReceiveThreeDSecurePaymentUseCase.PAYMENT_COMPLETED_EVENT, this);
    }

    @Override
    public void receive(PaymentCompletionEvent message) {
        var invitationId = message.getConversationId();

        var invitation = repository.find(invitationId);
        updateInvitationPaymentStatus(invitation);

        paymentEmailSender.send("Payment successfully completed, Invitation will be prepared between 2-3 days.");
    }

    private void updateInvitationPaymentStatus(Invitation invitation) {
        invitation.setPaid(true);
        invitation.setStatus(Status.PENDING);

        repository.save(invitation);
    }

    @Override
    public Class<PaymentCompletionEvent> getMessageClass() {
        return PaymentCompletionEvent.class;
    }
}
