package br.upe.controllers;

import br.upe.pojos.Submission;

public class SubmissionController {
    private Submission currentSubmission;

    public Submission getCurrentSubmission() {
        return currentSubmission;
    }

    public void setCurrentSubmission(Submission submission) {
        this.currentSubmission = submission;
    }

    public void updateSubmission(Long userId, Long eventId) {
        if (currentSubmission != null) {
            currentSubmission.setUserId(userId);
            currentSubmission.setEventId(eventId);
        }
    }
}