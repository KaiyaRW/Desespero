package br.upe.controllers;

import br.upe.pojos.Subscription;

import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionController {

    private final DAOController daoController;

    public SubscriptionController(DAOController daoController) {
        this.daoController = daoController;
    }

    public Subscription subscribeToEvent(Subscription subscription) {
        return daoController.executeTransactionWithReturn(() -> {
            daoController.subscriptionDAO.save(subscription);
            return subscription;
        });
    }

    public List<Subscription> getAllEventSubscriptions() {
        return daoController.executeTransactionWithReturn(() ->
                daoController.subscriptionDAO.findAll().stream()
                        .filter(subscription -> subscription.getSession() == null)
                        .collect(Collectors.toList())
        );
    }
}