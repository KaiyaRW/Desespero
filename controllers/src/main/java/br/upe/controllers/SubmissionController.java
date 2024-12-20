package br.upe.controllers;

import br.upe.dao.SubmissionDAO;
import br.upe.pojos.Event;
import br.upe.pojos.Submission;
import br.upe.pojos.User;

import jakarta.persistence.EntityManager;

import java.util.List;

public class SubmissionController extends BaseController {

    private final SubmissionDAO submissionDAO;

    public SubmissionController(EntityManager entityManager) {
        super(entityManager);
        this.submissionDAO = new SubmissionDAO(entityManager);
    }

    public Submission addSubmission(User user, Event event, String titulo, String link) {
        return executeTransactionWithReturn(() -> {
            Submission submission = new Submission();
            submission.setUser(user);
            submission.setEvent(event);
            submission.setTitulo(titulo);
            submission.setLink(link);

            submissionDAO.save(submission);
            return submission;
        });
    }

    public void updateSubmissionTitulo(Long submissionId, String newTitulo) {
        executeTransaction(() -> {
            Submission submission = submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submission.setTitulo(newTitulo);
            submissionDAO.update(submission);
        });
    }

    public List<Submission> getAllSubmissions() {
        return executeTransactionWithReturn(submissionDAO::findAll);
    }

    public void removeSubmission(Long submissionId) {
        executeTransaction(() -> {
            Submission submission = submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submissionDAO.delete(submission);
        });
    }
}