package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.payment.model.Payment;

public interface PaymentProvider {
    String initiatePayment(Payment payment);

    void completePayment();
}
