package com.bilginyuksel.digitivation.payment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class Payment {
    private String id;
    private String resourceId;
    private Customer customer;
    private Card card;
    private double price;
    private double paidPrice;

    public void applyDiscount(double discountPercentage) {
        var discount = price * discountPercentage / 100;
        this.paidPrice = price - discount;
    }
}
