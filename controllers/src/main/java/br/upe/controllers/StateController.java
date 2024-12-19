package br.upe.controllers;

import br.upe.pojos.*;

public class StateController {
    public User currentUser;
    public Event currentEvent;
    public Session currentSession;
    public Submission currentSubmission;
    public Subscription currentSubscription;

    public Event getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentEvent(Event currentEvent) {
        this.currentEvent = currentEvent;
    }
    public Session getCurrentSession() {
        return currentSession;
    }
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }
    public void setCurrentSubscription(Subscription currentSubscription) {
        this.currentSubscription = currentSubscription;
    }
    public void setCurrentSubmission(Submission currentSubmission) {
        this.currentSubmission = currentSubmission;
    }
    public Submission getCurrentSubmission() {
        return currentSubmission;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}