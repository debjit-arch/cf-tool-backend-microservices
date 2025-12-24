package com.example.docservice.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoRoutingConfig {

    @Bean
    public Map<String, MongoDatabaseFactory> mongoFactories(
            @Value("${spring.data.mongodb.in.uri}") String inUri,
            @Value("${spring.data.mongodb.eu.uri}") String euUri,
            @Value("${spring.data.mongodb.us.uri}") String usUri) {

        Map<String, MongoDatabaseFactory> map = new HashMap<>();

        map.put("in", new SimpleMongoClientDatabaseFactory(inUri));
        map.put("eu", new SimpleMongoClientDatabaseFactory(euUri));
        map.put("us", new SimpleMongoClientDatabaseFactory(usUri));

        return map;
    }
    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(
            Map<String, MongoDatabaseFactory> factories) {
        return new RegionRoutingMongoDatabaseFactory(factories);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }
}
