package br.upe.controllers;

import br.upe.dao.SubmissionDAO;
import br.upe.pojos.Event;
import br.upe.pojos.Submission;

import br.upe.pojos.User;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SubmissionController {
    private final SubmissionDAO submissionDAO;
    private final EntityManager entityManager;

    public SubmissionController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.submissionDAO = new SubmissionDAO(entityManager);
    }

    public Submission addSubmission(User user, Event event, String titulo, String link) {
        entityManager.getTransaction().begin();
        try {
            Submission submission = new Submission();
            submission.setUser(user);
            submission.setEvent(event); // Usando o renomeado 'setEvent()'
            submission.setTitulo(titulo);
            submission.setLink(link);

            submissionDAO.save(submission); // Salva no banco
            entityManager.getTransaction().commit();
            return submission;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateSubmissionTitulo(Long submissionId, String newTitulo) {
        entityManager.getTransaction().begin();
        try {
            Submission submission = submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submission.setTitulo(newTitulo); // Atualiza o título
            submissionDAO.update(submission); // Persiste no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void updateSubmissionLink(Long submissionId, String newLink) {
        entityManager.getTransaction().begin();
        try {
            Submission submission = submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submission.setLink(newLink); // Atualiza o link
            submissionDAO.update(submission); // Persiste no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public List<Submission> getAllSubmissions() {
        return submissionDAO.findAll();
    }

    public void removeSubmission(Long submissionId) {
        entityManager.getTransaction().begin();
        try {
            Submission submission = submissionDAO.findById(submissionId);
            if (submission == null) {
                throw new IllegalArgumentException("Submissão não encontrada.");
            }
            submissionDAO.delete(submission); // Remove do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}