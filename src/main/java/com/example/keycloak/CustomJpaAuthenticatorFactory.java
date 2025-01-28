package com.example.keycloak;

import org.keycloak.Config;

import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.events.EventStoreProvider;
import org.keycloak.events.EventStoreProviderFactory;

import org.keycloak.events.jpa.JpaEventStoreProviderFactory;

import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomJpaAuthenticatorFactory extends JpaEventStoreProviderFactory implements EventStoreProviderFactory {

    private final Logger log = LoggerFactory.getLogger(CustomJpaAuthenticatorFactory.class);

    public EventStoreProvider create(KeycloakSession session) {
        log.info("create provider factory");
        System.out.println("create provider factory");
        JpaConnectionProvider connection = (JpaConnectionProvider)session.getProvider(JpaConnectionProvider.class);
        return new CustomJpaEventStoreProvider(session, connection.getEntityManager());
    }

    @Override
    public void init(Config.Scope scope) {
        super.init(scope);
        System.out.println("CustomJpaEventStoreProviderFactory initialized");

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "custom-jpa-event-store-provider";
    }
}
