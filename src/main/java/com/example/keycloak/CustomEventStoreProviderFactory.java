
package com.example.keycloak;

import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.events.jpa.JpaEventStoreProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.events.EventStoreProvider;
import org.keycloak.events.EventStoreProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEventStoreProviderFactory extends JpaEventStoreProviderFactory implements EventStoreProviderFactory {

    private final Logger log = LoggerFactory.getLogger(CustomEventListenerProviderFactory.class);

    @Override
    public EventStoreProvider create(KeycloakSession keycloakSession) {
        log.info("ALIENTTITY1: " + keycloakSession.getProvider(JpaConnectionProvider.class).getEntityManager());
        System.out.println("ALIENTITIY1: " + keycloakSession.getProvider(JpaConnectionProvider.class).getEntityManager());
        return new CustomEventStoreProvider(keycloakSession);
    }

    @Override
    public void init(org.keycloak.Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // Post-initialization logic
    }

    @Override
    public void close() {
        // Cleanup if required
    }

    @Override
    public String getId() {
        return "custom-jpa-event-store";
    }
}
