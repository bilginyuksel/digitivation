package com.bilginyuksel.digitivation.adapter.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
}
