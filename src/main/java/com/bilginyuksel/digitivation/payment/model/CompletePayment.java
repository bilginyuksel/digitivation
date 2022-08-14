package com.bilginyuksel.digitivation.payment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class CompletePayment {
    private String paymentId;
    private String resourceId;
    private String extras;
}
