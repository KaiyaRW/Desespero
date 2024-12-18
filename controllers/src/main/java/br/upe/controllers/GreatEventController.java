package br.upe.controllers;

import br.upe.pojos.GreatEvent;
import br.upe.pojos.Session;
import br.upe.pojos.Submission;

import java.util.Collection;

public class GreatEventController {
    private GreatEvent currentEvent;

    public GreatEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(GreatEvent event) {
        this.currentEvent = event;
    }

    public Collection<Session> getSessions() {
        return currentEvent != null ? currentEvent.getSessions() : null;
    }

    public Collection<Submission> getSubmissions() {
        return currentEvent != null ? currentEvent.getSubmissions() : null;
    }

    public void addSession(Session session) {
        if (currentEvent != null) {
            currentEvent.addSession(session);
        }
    }

    public void addSubmission(Submission submission) {
        if (currentEvent != null) {
            currentEvent.addSubmission(submission);
        }
    }
}