package br.upe.controllers;

import br.upe.dao.CommonUserDAO;
import br.upe.pojos.CommonUser;
import jakarta.persistence.EntityManager;

public class CommonUserController {
    private final CommonUserDAO commonUserDAO;
    private final EntityManager entityManager;

    public CommonUserController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    /**
     * Atualiza o nome do usuário.
     * @param userId ID do usuário.
     * @param newName Novo nome a ser atribuído.
     */
    public void updateUserName(Long userId, String newName) {
        entityManager.getTransaction().begin();
        try {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setName(newName);
            commonUserDAO.update(user); // Atualiza o usuário no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza o email do usuário.
     * @param userId ID do usuário.
     * @param newEmail Novo email a ser atribuído.
     */
    public void updateUserEmail(Long userId, String newEmail) {
        entityManager.getTransaction().begin();
        try {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setEmail(newEmail);
            commonUserDAO.update(user); // Atualiza o usuário no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a senha do usuário.
     * @param userId ID do usuário.
     * @param newPassword Nova senha a ser atribuída.
     */
    public void updateUserPassword(Long userId, String newPassword) {
        entityManager.getTransaction().begin();
        try {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setPassword(newPassword);
            commonUserDAO.update(user); // Atualiza o usuário no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Remove o usuário pelo ID.
     * @param userId ID do usuário a ser removido.
     */
    public void deleteUser(Long userId) {
        entityManager.getTransaction().begin();
        try {
            CommonUser user = commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            commonUserDAO.delete(user); // Remove o usuário do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}