package com.bilginyuksel.digitivation.infra.adapter.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("iyzipay")
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class IyzipayConfiguration {
    private String apiKey;
    private String secretKey;
    private String baseURL;
    private String callbackURL;
    private Address billingAddress;

    @Getter
    @Setter
    public static class Address {
        private String city;
        private String country;
        private String address;
        private String contactName;
    }
}
