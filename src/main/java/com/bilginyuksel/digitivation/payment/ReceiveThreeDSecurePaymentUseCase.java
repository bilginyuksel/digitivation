package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.pubsub.Broker;
import com.bilginyuksel.digitivation.BusinessUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiveThreeDSecurePaymentUseCase implements BusinessUseCase<String, String> {
    public static String PAYMENT_COMPLETED_EVENT = "payment_completed";

    private Broker broker;
    private PaymentRepository paymentRepository;
    private PaymentEmailSender paymentEmailSender;
    private PaymentProvider paymentProvider;

    @Override
    public String handle(String s) {
        broker.publish(s, s);
        paymentRepository.save(s);
        paymentProvider.completePayment();
        paymentEmailSender.send(s);
        return null;
    }
}
