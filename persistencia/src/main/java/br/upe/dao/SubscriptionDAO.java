package br.upe.dao;

import br.upe.pojos.Subscription;

import jakarta.persistence.EntityManager;

public class SubscriptionDAO extends GenericDAO<Subscription> {

    public SubscriptionDAO(EntityManager entityManager) {
        super(Subscription.class, entityManager);
    }

}