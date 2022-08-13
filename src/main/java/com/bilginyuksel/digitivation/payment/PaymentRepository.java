package com.bilginyuksel.digitivation.payment;

import com.bilginyuksel.digitivation.payment.model.Payment;

public interface PaymentRepository {
    void save(Payment payment);
}
