package br.upe.controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public abstract class BaseController {

    protected final EntityManager entityManager;

    public BaseController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void executeTransaction(Runnable action) {
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
            throw new RuntimeException("Erro durante execução de transação", e);
        }
    }

    protected <T> T executeTransactionWithReturn(ReturnableAction<T> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            T result = action.run();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro durante execução de transação", e);
        }
    }

    @FunctionalInterface
    public interface ReturnableAction<T> {
        T run();
    }
}