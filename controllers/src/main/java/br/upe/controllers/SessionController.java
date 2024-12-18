package br.upe.controllers;

import br.upe.pojos.Session;
import br.upe.pojos.Subscription;

import java.util.Collection;

public class SessionController {
    private Session currentSession;

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session session) {
        this.currentSession = session;
    }

    public Collection<Subscription> getSubscriptions() {
        return currentSession != null ? currentSession.getSubscriptions() : null;
    }

    public void addSubscription(Subscription subscription) {
        if (currentSession != null) {
            currentSession.addSubscription(subscription);
        }
    }

    public void updateSessionDates(String descritor, Long sessionId) {
        if (currentSession != null) {
            currentSession.setDescritor(descritor);
            currentSession.setId(sessionId);
        }
    }
}