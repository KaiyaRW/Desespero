package br.upe.controllers;

import br.upe.dao.CommonUserDAO;
import br.upe.pojos.CommonUser;

import jakarta.persistence.EntityManager;

public class CommonUserController extends BaseController {

    private final CommonUserDAO commonUserDAO;

    public CommonUserController(EntityManager entityManager) {
        super(entityManager);
        this.commonUserDAO = new CommonUserDAO(entityManager);
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