package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.payment.PaymentProvider;
import org.springframework.stereotype.Component;

@Component
public class IyzipayPaymentProviderAdapter implements PaymentProvider {
    @Override
    public void initiatePayment() {

    }

    @Override
    public void completePayment() {

    }
}
