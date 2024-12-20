package br.upe.controllers;

import br.upe.dao.SubscriptionDAO;
import br.upe.pojos.Event;
import br.upe.pojos.Session;
import br.upe.pojos.Subscription;
import br.upe.pojos.User;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionController extends BaseController {

    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionController(EntityManager entityManager) {
        super(entityManager);
        this.subscriptionDAO = new SubscriptionDAO(entityManager);
    }

    public Subscription subscribeToEvent(User user, Event event) {
        return executeTransactionWithReturn(() -> {
            Subscription subscription = new Subscription();
            subscription.setUser(user);
            subscription.setEvent(event);
            subscription.setDate(new Date());

            subscriptionDAO.save(subscription);
            return subscription;
        });
    }

    public List<Subscription> getAllEventSubscriptions() {
        return executeTransactionWithReturn(() -> subscriptionDAO.findAll()
                .stream()
                .filter(subscription -> subscription.getSession() == null)
                .collect(Collectors.toList()));
    }

    public void removeEventSubscription(Long subscriptionId) {
        executeTransaction(() -> {
            Subscription subscription = subscriptionDAO.findById(subscriptionId);
            if (subscription == null || subscription.getSession() != null) {
                throw new IllegalArgumentException("Inscrição não encontrada ou pertence a uma sessão.");
            }
            subscriptionDAO.delete(subscription);
        });
    }
}