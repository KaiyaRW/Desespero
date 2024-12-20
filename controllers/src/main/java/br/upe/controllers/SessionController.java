package br.upe.controllers;

import br.upe.pojos.Session;

import java.util.Date;
import java.util.List;

public class SessionController {

    private final DAOController daoController;

    public SessionController(DAOController daoController) {
        this.daoController = daoController;
    }

    public Session createSession(String descritor, Date startDate, Date endDate) {
        return daoController.executeTransactionWithReturn(() -> {
            Session session = new Session();
            session.setDescritor(descritor);
            session.setStartDate(startDate);
            session.setEndDate(endDate);

            daoController.sessionDAO.save(session);
            return session;
        });
    }

    public void updateSessionDescritor(Long sessionId, String newDescritor) {
        daoController.executeTransaction(() -> {
            Session session = daoController.sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setDescritor(newDescritor);
            daoController.sessionDAO.update(session);
        });
    }

    public void deleteSession(Long sessionId) {
        daoController.executeTransaction(() -> {
            Session session = daoController.sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            daoController.sessionDAO.delete(session);
        });
    }

    public void updateSessionStartDate(Long sessionId, Date newStartDate) {
        daoController.executeTransaction(() -> {
            Session session = daoController.sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setStartDate(newStartDate);
            daoController.sessionDAO.update(session);
        });
    }

    public void updateSessionEndDate(Long sessionId, Date newEndDate) {
        daoController.executeTransaction(() -> {
            Session session = daoController.sessionDAO.findById(sessionId);
            if (session == null) {
                throw new IllegalArgumentException("Sessão não encontrada.");
            }
            session.setEndDate(newEndDate);
            daoController.sessionDAO.update(session);
        });
    }

    public List<Session> getAllSessions() {
        return daoController.executeTransactionWithReturn(() -> daoController.sessionDAO.findAll());
    }
}