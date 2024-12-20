package br.upe.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import br.upe.dao.*;

import java.util.function.Supplier;

public class DAOController {
    private static final String PERSISTENCE_UNIT_NAME = "default"; // Nome do persistence-unit
    private final EntityManagerFactory emf;
    private final EntityManager entityManager;

    public final EventDAO eventDAO;
    public final SessionDAO sessionDAO;
    public final SubmissionDAO submissionDAO;
    public final SubscriptionDAO subscriptionDAO;
    public final AdminUserDAO adminUserDAO;
    public final CommonUserDAO commonUserDAO;

    public DAOController() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = emf.createEntityManager();

        // Inicializa os DAOs com o mesmo EntityManager
        this.eventDAO = new EventDAO(entityManager);
        this.sessionDAO = new SessionDAO(entityManager);
        this.submissionDAO = new SubmissionDAO(entityManager);
        this.subscriptionDAO = new SubscriptionDAO(entityManager);
        this.adminUserDAO = new AdminUserDAO(entityManager);
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Fecha o EntityManager e o EntityManagerFactory.
     */
    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Executa uma transação sem valor de retorno.
     *
     * @param action A operação a ser executada dentro da transação.
     */
    public void executeTransaction(Runnable action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            action.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao executar transação: " + e.getMessage(), e);
        }
    }

    /**
     * Executa uma transação com retorno de valor.
     *
     * @param action A operação a ser executada que retorna o valor dentro da transação.
     * @param <T>    O tipo do valor retornado.
     * @return O valor retornado pela operação.
     */
    public <T> T executeTransactionWithReturn(Supplier<T> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            T result = action.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao executar transação: " + e.getMessage(), e);
        }
    }
}