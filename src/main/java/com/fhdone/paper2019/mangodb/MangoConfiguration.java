package com.fhdone.paper2019.mangodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MangoConfiguration extends AbstractMongoClientConfiguration {

    @Override
    @Bean(name = "mongoClient")
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Override
    protected String getDatabaseName() {
        return "mycol";
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), "mycol");
    }

}
