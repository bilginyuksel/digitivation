package com.bilginyuksel.digitivation.payment.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "create", builderClassName = "Builder")
public class Customer {
    private String id;
    private String name;
    private String surname;
    private String gsmNumber;
    private String email;
    private String identityNumber;
    private String ipAddress;
    private String city;
    private String country;
    private String registrationAddress;
}
