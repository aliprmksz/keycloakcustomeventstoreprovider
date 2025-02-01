
package com.example.keycloak;

import com.example.keycloak.model.EventOutbox;
import jakarta.persistence.EntityManager;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.events.Event;
import org.keycloak.events.EventQuery;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.EventStoreProvider;
import org.keycloak.events.admin.AdminEventQuery;
import org.keycloak.events.jpa.JpaEventStoreProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;

import java.util.UUID;

public class CustomEventStoreProvider extends JpaEventStoreProvider implements EventStoreProvider {

    private EntityManager entityManager;
    public CustomEventStoreProvider(KeycloakSession session) {
        super(session, session.getProvider(JpaConnectionProvider.class).getEntityManager());
        this.entityManager = session.getProvider(JpaConnectionProvider.class).getEntityManager();
    }

    @Override
    public void onEvent(Event event) {
        super.onEvent(event);
        EventOutbox entity = new EventOutbox();
        entity.setType(entity.getType());
        entity.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        entity.setEventId(event.getId() != null ? UUID.randomUUID().toString() : event.getId());
        entity.setTime(event.getTime());
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        super.onEvent(adminEvent, includeRepresentation);
        EventOutbox entity = new EventOutbox();
        entity.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        entity.setType(adminEvent.getOperationType().toString());
        entity.setTime(adminEvent.getTime());
        entity.setEventId(adminEvent.getId() == null ? UUID.randomUUID().toString() : adminEvent.getId());
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Override
    public EventQuery createQuery() {
        return null;
    }

    @Override
    public AdminEventQuery createAdminQuery() {
        return null;
    }

    @Override
    public void clear() {
        // Optional: Clear stored events if needed.
    }

    @Override
    public void clear(RealmModel realmModel) {

    }

    @Override
    public void clear(RealmModel realmModel, long l) {

    }

    @Override
    public void clearExpiredEvents() {

    }

    @Override
    public void clearAdmin() {

    }

    @Override
    public void clearAdmin(RealmModel realmModel) {

    }

    @Override
    public void clearAdmin(RealmModel realmModel, long l) {

    }

    @Override
    public void close() {
        // Cleanup resources if needed.
    }
}