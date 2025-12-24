package com.example.docservice.config;

import java.util.Map;

import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mongodb.MongoDatabaseFactory;

import com.example.docservice.region.RegionContext;
import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoDatabase;

public class RegionRoutingMongoDatabaseFactory implements MongoDatabaseFactory {

    private final Map<String, MongoDatabaseFactory> factories;

    public RegionRoutingMongoDatabaseFactory(
            Map<String, MongoDatabaseFactory> factories) {
        this.factories = factories;
    }

    private MongoDatabaseFactory getTargetFactory() {
        String region = RegionContext.get();
        return factories.getOrDefault(region, factories.get("in"));
    }

    @Override
    public MongoDatabase getMongoDatabase() {
        return getTargetFactory().getMongoDatabase();
    }

    @Override
    public MongoDatabase getMongoDatabase(String dbName) {
        return getTargetFactory().getMongoDatabase(dbName);
    }

    @Override
    public ClientSession getSession(ClientSessionOptions options) {
        return getTargetFactory().getSession(options);
    }

    @Override
    public MongoDatabaseFactory withSession(ClientSession session) {
        return getTargetFactory().withSession(session);
    }

    @Override
    public PersistenceExceptionTranslator getExceptionTranslator() {
        return getTargetFactory().getExceptionTranslator();
    }
}
