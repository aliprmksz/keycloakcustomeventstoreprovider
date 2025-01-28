package com.example.keycloak;

import com.example.keycloak.model.EventOutbox;
import org.keycloak.events.Event;
import org.keycloak.events.EventStoreProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.jpa.JpaEventStoreProvider;
import org.keycloak.models.KeycloakSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.UUID;

public class CustomJpaEventStoreProvider extends JpaEventStoreProvider implements EventStoreProvider {

    private final KeycloakSession session;
    private final EntityManager em;

    private final Logger log = LoggerFactory.getLogger(CustomJpaEventStoreProvider.class);
    public CustomJpaEventStoreProvider(KeycloakSession session, EntityManager entityManager) {
        super(session, entityManager, 20, 20);
        this.session = session;
        this.em = entityManager;
    }

    @Override
    public void onEvent(Event event) {
        super.onEvent(event);
        System.out.println("on user event");
        log.info("on user event");
        log.error("on user event");
        EventOutbox eventOutbox = new EventOutbox();
        eventOutbox.setId(event.getId() == null ? UUID.randomUUID().toString() : event.getId());
        eventOutbox.setTime(event.getTime());
        eventOutbox.setType(event.getType().toString());
        this.em.persist(eventOutbox);
        this.em.flush();
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        super.onEvent(adminEvent, includeRepresentation);
        System.out.println("on user event");
        log.info("on admin event");
        log.error("on admin event");
        EventOutbox eventOutbox = new EventOutbox();
        eventOutbox.setId(adminEvent.getId() == null ? UUID.randomUUID().toString() : adminEvent.getId());
        eventOutbox.setTime(adminEvent.getTime());
        eventOutbox.setType(adminEvent.getOperationType().toString());
        this.em.persist(eventOutbox);
        this.em.flush();
    }


}
