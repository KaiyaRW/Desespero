package br.upe.controllers;

import br.upe.pojos.Subscription;

public class SubscriptionController {
    private Subscription currentSubscription;

    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }

    public void setCurrentSubscription(Subscription subscription) {
        this.currentSubscription = subscription;
    }

    public void updateSubscription(Long userId, Long sessionId) {
        if (currentSubscription != null) {
            currentSubscription.setUserId(userId);
            currentSubscription.setSessionId(sessionId);
        }
    }
}