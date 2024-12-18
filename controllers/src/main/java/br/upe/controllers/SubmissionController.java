package br.upe.controllers;

import br.upe.dao.SubmissionDAO;
import br.upe.pojos.Submission;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class SubmissionController {
    private final SubmissionDAO submissionDAO;
    private final EntityManager entityManager;

    public SubmissionController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.submissionDAO = new SubmissionDAO(entityManager);
    }

    /**
     * Adiciona (cria) uma nova submissão.
     *
     * @param userId ID do usuário que está enviando a submissão.
     * @param eventId ID do evento relacionado à submissão.
     * @param titulo Título da submissão.
     * @param link Link para a submissão (ex.: arquivo ou repositório).
     * @return A submissão criada.
     */
    public Submission addSubmission(Long userId, Long eventId, String titulo, String link) {
        entityManager.getTransaction().begin();
        try {
            Submission submission = new Submission();
            submission.setUserId(userId);
            submission.setEventId(eventId);
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

    /**
     * Atualiza o título de uma submissão específica.
     *
     * @param submissionId ID da submissão a ser atualizada.
     * @param newTitulo Novo título.
     */
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

    /**
     * Atualiza o link de uma submissão específica.
     *
     * @param submissionId ID da submissão a ser atualizada.
     * @param newLink Novo link.
     */
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

    /**
     * Lista todas as submissões cadastradas.
     *
     * @return Lista contendo todas as submissões.
     */
    public List<Submission> getAllSubmissions() {
        try {
            return submissionDAO.findAll(); // Retorna todas as submissões
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar todas as submissões.", e);
        }
    }

    /**
     * Remove uma submissão pelo ID.
     *
     * @param submissionId ID da submissão a ser removida.
     */
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