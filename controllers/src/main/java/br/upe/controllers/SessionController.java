package br.upe.controllers;

import br.upe.dao.SessionDAO;
import br.upe.pojos.Session;

import jakarta.persistence.EntityManager;

import java.util.Date;

public class SessionController extends BaseController {

    private final SessionDAO sessionDAO;

    public SessionController(EntityManager entityManager) {
        super(entityManager);
        this.sessionDAO = new SessionDAO(entityManager);
    }

    public Session createSession(String descritor, Date startDate, Date endDate) {
        return executeTransactionWithReturn(() -> {
            Session session = new Session();
            session.setDescritor(descritor);
            session.setStartDate(startDate);
            session.setEndDate(endDate);

            sessionDAO.save(session);
            return session;
        });
    }

    public void updateSessionDescritor(Long sessionId, String newDescritor) {
        executeTransaction(() -> {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setDescritor(newDescritor);
            sessionDAO.update(session);
        });
    }

    public void deleteSession(Long sessionId) {
        executeTransaction(() -> {
            Session session = sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            sessionDAO.delete(session);
        });
    }
}