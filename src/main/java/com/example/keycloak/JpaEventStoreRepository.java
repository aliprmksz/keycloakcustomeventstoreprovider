package com.example.keycloak;

import com.example.keycloak.model.CustomEventEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Transactional
public class JpaEventStoreRepository {

   @PersistenceContext
  private EntityManager entityManager;

    public void save(CustomEventEntity event) {
        entityManager.persist(event);
    }
}
