package br.upe.controllers;

import br.upe.dao.SessionDAO;
import br.upe.pojos.Session;
import jakarta.persistence.EntityManager;

import java.util.Date;

public class SessionController {
    private final SessionDAO sessionDAO;
    private final EntityManager entityManager;

    public SessionController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.sessionDAO = new SessionDAO(entityManager);
    }

    /**
     * Cria uma nova sessão e salva no banco de dados.
     *
     * @param descritor Descritor da sessão.
     * @param startDate Data de início da sessão.
     * @param endDate Data de fim da sessão.
     * @return A sessão criada.
     */
    public Session createSession(String descritor, Date startDate, Date endDate) {
        entityManager.getTransaction().begin();
        try {
            Session session = new Session();
            session.setDescritor(descritor);
            session.setStartDate(startDate);
            session.setEndDate(endDate);

            sessionDAO.save(session); // Salva a nova sessão no banco
            entityManager.getTransaction().commit();
            return session;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza o descritor da sessão existente.
     *
     * @param sessionId ID da sessão.
     * @param newDescritor Novo descritor.
     */
    public void updateSessionDescritor(Long sessionId, String newDescritor) {
        entityManager.getTransaction().begin();
        try {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setDescritor(newDescritor);
            sessionDAO.update(session); // Atualiza a sessão no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a data de início da sessão.
     *
     * @param sessionId ID da sessão.
     * @param newStartDate Nova data de início.
     */
    public void updateSessionStartDate(Long sessionId, Date newStartDate) {
        entityManager.getTransaction().begin();
        try {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setStartDate(newStartDate);
            sessionDAO.update(session); // Atualiza a sessão no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a data de fim da sessão.
     *
     * @param sessionId ID da sessão.
     * @param newEndDate Nova data de fim.
     */
    public void updateSessionEndDate(Long sessionId, Date newEndDate) {
        entityManager.getTransaction().begin();
        try {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setEndDate(newEndDate);
            sessionDAO.update(session); // Atualiza a sessão no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Remove uma sessão com base no ID.
     *
     * @param sessionId ID da sessão a ser removida.
     */
    public void deleteSession(Long sessionId) {
        entityManager.getTransaction().begin();
        try {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            sessionDAO.delete(session); // Remove a sessão do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}