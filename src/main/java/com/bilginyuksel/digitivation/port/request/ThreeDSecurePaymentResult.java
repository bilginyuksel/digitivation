package com.bilginyuksel.digitivation.port.request;

public record ThreeDSecurePaymentResult(
    String status,
    String paymentId,
    String resourceId,
    String mdStatus,
    String conversationData
) {
}
