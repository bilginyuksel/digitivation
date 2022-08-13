package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.BusinessUseCase;
import com.bilginyuksel.digitivation.payment.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitiateThreeDSecurePaymentUseCase implements BusinessUseCase<Payment, String> {
    private PaymentRepository paymentRepository;
    private PaymentProvider paymentProvider;

    @Override
    public String handle(Payment payment) {
        var htmlContent = paymentProvider.initiatePayment(payment);

        paymentRepository.save(payment);

        return htmlContent;
    }
}
