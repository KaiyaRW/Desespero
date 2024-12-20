package br.upe.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import br.upe.dao.*;

public class DAOController {
    private static final String PERSISTENCE_UNIT_NAME = "default"; // Nome do persistence-unit
    private final EntityManager entityManager;
    private final EntityManagerFactory emf;


    public final EventDAO eventDAO;
    public final SessionDAO sessionDAO;
    public final SubmissionDAO submissionDAO;
    public final SubscriptionDAO subscriptionDAO;
    public final AdminUserDAO adminUserDAO;
    public final CommonUserDAO commonUserDAO;

    public DAOController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = emf.createEntityManager();

        // Inicializa DAOs
        this.eventDAO = new EventDAO(entityManager);
        this.sessionDAO = new SessionDAO(entityManager);
        this.submissionDAO = new SubmissionDAO(entityManager);
        this.subscriptionDAO = new SubscriptionDAO(entityManager);
        this.adminUserDAO = new AdminUserDAO(entityManager);
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}