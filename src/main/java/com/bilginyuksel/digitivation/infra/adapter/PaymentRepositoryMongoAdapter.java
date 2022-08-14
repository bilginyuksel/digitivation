package com.bilginyuksel.digitivation.infra.adapter;

import com.bilginyuksel.digitivation.payment.PaymentRepository;
import com.bilginyuksel.digitivation.payment.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryMongoAdapter implements PaymentRepository {
    @Override
    public void save(Payment payment) {

    }
}
