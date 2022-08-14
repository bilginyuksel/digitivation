package com.bilginyuksel.digitivation.port.request;

import com.bilginyuksel.digitivation.payment.model.Card;
import com.bilginyuksel.digitivation.payment.model.Customer;
import com.bilginyuksel.digitivation.payment.model.Payment;

public record ThreeDSecurePaymentRequest(
        String resourceId,
        String cardNumber,
        String expireYear,
        String expireMonth,
        String cvc,
        String cardHolderName,
        String buyerName,
        String buyerSurname,
        String buyerGsmNumber,
        String buyerEmail,
        String buyerIdentityNumber,
        String buyerCity,
        String buyerCountry,
        String buyerRegistrationAddress,
        double price,
        double paidPrice
) {
    public Payment toPayment(String ipAddress) {
        return Payment.create()
                .resourceId(this.resourceId())
                .price(price)
                .paidPrice(paidPrice)
                .customer(Customer.create()
                        .id(this.buyerEmail()) // use email as buyer id
                        .city(this.buyerCity())
                        .country(this.buyerCountry())
                        .email(this.buyerEmail())
                        .gsmNumber(this.buyerGsmNumber())
                        .name(this.buyerName())
                        .surname(this.buyerSurname())
                        .identityNumber(this.buyerIdentityNumber())
                        .ipAddress(ipAddress)
                        .registrationAddress(this.buyerRegistrationAddress())
                        .build())
                .card(Card.create()
                        .cardNumber(this.cardNumber())
                        .cardHolderName(this.cardHolderName())
                        .expireMonth(this.expireMonth())
                        .expireYear(this.expireYear())
                        .cvc(this.cvc())
                        .build())
                .build();
    }
}
