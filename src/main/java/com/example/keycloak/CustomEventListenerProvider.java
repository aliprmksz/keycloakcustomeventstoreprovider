
package com.example.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventStoreProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class CustomEventListenerProvider implements EventListenerProvider {

    private final KeycloakSession keycloakSession;
    private final Logger log = LoggerFactory.getLogger(CustomEventListenerProvider.class);

    public CustomEventListenerProvider(KeycloakSession keycloakSession) {
        this.keycloakSession = keycloakSession;
        log.info("created custom event listener provider instance");
    }

    @Override
    public void onEvent(Event event) {
        Set<EventStoreProvider> providers = this.keycloakSession.getAllProviders(EventStoreProvider.class);
        providers.stream().filter(clazz -> clazz instanceof CustomEventStoreProvider).forEach(prv -> {
            log.info("admin provider is: " + prv);
            prv.onEvent(event);
        });
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        Set<EventStoreProvider> providers = this.keycloakSession.getAllProviders(EventStoreProvider.class);
        providers.stream().filter(clazz -> clazz instanceof CustomEventStoreProvider).forEach(prv -> {
            log.info("admin provider is: " + prv);
            prv.onEvent(adminEvent, includeRepresentation);
        });
    }

    @Override
    public void close() {
        // Cleanup if needed
    }
}
