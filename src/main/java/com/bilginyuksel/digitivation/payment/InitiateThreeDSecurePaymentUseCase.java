package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.BusinessUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InitiateThreeDSecurePaymentUseCase implements BusinessUseCase<String, String> {
    private PaymentRepository paymentRepository;
    private PaymentProvider paymentProvider;

    @Override
    public String handle(String s) {
        return null;
    }
}
