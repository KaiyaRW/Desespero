package br.upe.controllers;

import br.upe.pojos.Event;
import br.upe.pojos.Submission;
import br.upe.pojos.User;

import java.util.List;

public class SubmissionController {

    private final DAOController daoController;

    public SubmissionController(DAOController daoController) {
        this.daoController = daoController;
    }

    public Submission addSubmission(User user, Event event, String titulo, String link) {
        return daoController.executeTransactionWithReturn(() -> {
            Submission submission = new Submission();
            submission.setUser(user);
            submission.setEvent(event);
            submission.setTitulo(titulo);
            submission.setLink(link);

            daoController.submissionDAO.save(submission);
            return submission;
        });
    }

    public void updateSubmissionTitulo(Long submissionId, String newTitulo) {
        daoController.executeTransaction(() -> {
            Submission submission = daoController.submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submission.setTitulo(newTitulo);
            daoController.submissionDAO.update(submission);
        });
    }

    public List<Submission> getAllSubmissions() {
        return daoController.executeTransactionWithReturn(daoController.submissionDAO::findAll);
    }

    public void removeSubmission(Long submissionId) {
        daoController.executeTransaction(() -> {
            Submission submission = daoController.submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            daoController.submissionDAO.delete(submission);
        });
    }
}