package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.payment.PaymentEmailSender;
import org.springframework.stereotype.Component;

@Component
public class PaymentEmailSenderAdapter implements PaymentEmailSender {
    @Override
    public void send(String emailContent) {

    }
}
