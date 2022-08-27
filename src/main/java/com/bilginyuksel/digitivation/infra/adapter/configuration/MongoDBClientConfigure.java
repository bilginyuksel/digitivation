package com.bilginyuksel.digitivation.infra.adapter.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
@RequiredArgsConstructor
public class MongoDBClientConfigure extends AbstractMongoClientConfiguration {
    private final MongoConfiguration configuration;

    @Override
    public MongoClient mongoClient() {
        var connectionString = new ConnectionString(configuration.getConnectionURI());
        var settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        return configuration.getDatabase();
    }
}
