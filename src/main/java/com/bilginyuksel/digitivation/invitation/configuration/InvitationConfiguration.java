package com.bilginyuksel.digitivation.invitation.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties("invitation")
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class InvitationConfiguration {
    private double basePrice;
    private double discount;
}
