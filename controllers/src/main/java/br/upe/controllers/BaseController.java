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
            throw new RuntimeException("Erro ao executar transação: " + e.getMessage(), e);
        }
    }

    protected <T> T executeTransactionWithReturn(TransactionAction<T> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            T result = action.execute();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao executar transação: " + e.getMessage(), e);
        }
    }

    @FunctionalInterface
    protected interface TransactionAction<T> {
        T execute();
    }
}