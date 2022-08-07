package com.bilginyuksel.digitivation.payment.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class PaymentCompletionEvent {
    /**
     * Conversation id is used to track paid resource information
     * An id of the resource will be sent to payment provider on initiatePayment call
     * After we receive the payment information conversation id will be there
     * and could be used to access paid resource
     */
    private String conversationId;
    private String paymentId;
    private String status;
}
