package com.fhdone.paper2019.mangodb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.SocketSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class MangoConfiguration extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "mycol";
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        MongoClientSettings mongoSettingsProperties = getMongoClientSettings();
        MongoClient mongoClient = MongoClients.create(mongoSettingsProperties);
        return new MongoTemplate(mongoClient, "mycol");
    }

    private MongoClientSettings getMongoClientSettings() {
        return MongoClientSettings.builder()
                .applicationName("es-demo")
//                .applyToSslSettings(sslBuilder ->
//                        SslSettings.builder().
//                                enabled(sslEnabled).
//                                invalidHostNameAllowed(false).build())
                .applyConnectionString(
                        new ConnectionString("mongodb://localhost:27017")
                )
                .applyToConnectionPoolSettings(connPoolBuilder ->
                        ConnectionPoolSettings.builder().
                                maxWaitTime(30, TimeUnit.SECONDS).
                                maxSize(10).
                                maxSize(20).build())
                .applyToSocketSettings(socketBuilder ->
                        SocketSettings.builder().
                                connectTimeout(30,TimeUnit.SECONDS).build())
                .readPreference(ReadPreference.secondaryPreferred())
                .build();
    }

}
