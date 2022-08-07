package com.bilginyuksel.digitivation.payment;

public interface PaymentProvider {
    void initiatePayment();

    void completePayment();
}
