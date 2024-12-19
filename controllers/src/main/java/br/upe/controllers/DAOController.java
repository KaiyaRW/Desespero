package br.upe.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import br.upe.dao.*;

public class DAOController {
    private static final String PERSISTENCE_UNIT_NAME = "default"; // Nome do persistence-unit
    private final EntityManager entityManager;

    public final EventDAO eventDAO;
    public final SessionDAO sessionDAO;
    public final SubmissionDAO submissionDAO;
    public final SubscriptionDAO subscriptionDAO;
    public final AdminUserDAO adminUserDAO;
    public final CommonUserDAO commonUserDAO;

    public DAOController() {
        // Inicializa o EntityManagerFactory e cria o EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = emf.createEntityManager();

        // Passa o EntityManager para os DAOs que precisam dele
        this.eventDAO = new EventDAO(entityManager);
        this.sessionDAO = new SessionDAO(entityManager);
        this.submissionDAO = new SubmissionDAO(entityManager);
        this.subscriptionDAO = new SubscriptionDAO(entityManager);
        this.adminUserDAO = new AdminUserDAO(entityManager);
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    // Fecha o EntityManager quando o DAOController for descartado
    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}