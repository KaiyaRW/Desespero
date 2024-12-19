package br.upe.controllers;

import br.upe.dao.CommonUserDAO;
import br.upe.pojos.CommonUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CommonUserController {
    private final CommonUserDAO commonUserDAO;
    private final EntityManager entityManager;

    public CommonUserController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    private void executeTransaction(Runnable action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            action.run();
            if (transaction.isActive()) {
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro durante execução de transação: " + e.getMessage(), e);
        }
    }

    public void updateUserName(Long userId, String newName) {
        executeTransaction(() -> {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setName(newName);
            commonUserDAO.update(user);
        });
    }

    public void updateUserEmail(Long userId, String newEmail) {
        executeTransaction(() -> {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setEmail(newEmail);
            commonUserDAO.update(user);
        });
    }

    public void updateUserPassword(Long userId, String newPassword) {
        executeTransaction(() -> {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setPassword(newPassword);
            commonUserDAO.update(user);
        });
    }

    public void deleteUser(Long userId) {
        executeTransaction(() -> {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            commonUserDAO.delete(user);
        });
    }
}