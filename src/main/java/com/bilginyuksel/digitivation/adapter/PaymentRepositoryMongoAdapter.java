package com.bilginyuksel.digitivation.adapter;

import com.bilginyuksel.digitivation.payment.PaymentRepository;
import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryMongoAdapter implements PaymentRepository {
    @Override
    public void save(String payment) {

    }
}
