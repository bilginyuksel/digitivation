package com.bilginyuksel.digitivation.infra.port.request;

import com.bilginyuksel.digitivation.payment.model.CompletePayment;

public record ThreeDSecurePaymentResult(
        String status,
        String paymentId,
        String resourceId,
        String mdStatus,
        String conversationData
) {
    public CompletePayment toCompletePayment() {
        return CompletePayment.create()
                .paymentId(paymentId)
                .resourceId(resourceId)
                .extras(conversationData)
                .build();
    }
}
