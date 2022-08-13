package com.bilginyuksel.digitivation.payment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class Card {
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;
    private String cardHolderName;
}
