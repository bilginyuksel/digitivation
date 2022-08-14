package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.payment.model.CompletePayment;
import com.bilginyuksel.digitivation.pubsub.Broker;
import com.bilginyuksel.digitivation.BusinessUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiveThreeDSecurePaymentUseCase implements BusinessUseCase<CompletePayment, String> {
    public static String PAYMENT_COMPLETED_EVENT = "payment_completed";

    private Broker broker;
    private PaymentRepository paymentRepository;
    private PaymentEmailSender paymentEmailSender;
    private PaymentProvider paymentProvider;

    @Override
    public String handle(CompletePayment completePayment) {
        paymentProvider.completePayment(completePayment);
        paymentRepository.save(null);

        broker.publish("event", completePayment);

        paymentEmailSender.send("Payment completed successfully.");
        return null;
    }
}
