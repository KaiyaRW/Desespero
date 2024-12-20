package br.upe.controllers;

import br.upe.pojos.CommonUser;

public class CommonUserController {

    private final DAOController daoController;

    public CommonUserController(DAOController daoController) {
        this.daoController = daoController;
    }

    public void updateUserName(Long userId, String newName) {
        daoController.executeTransaction(() -> {
            CommonUser user = daoController.commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setName(newName);
            daoController.commonUserDAO.update(user);
        });
    }

    public void updateUserEmail(Long userId, String newEmail) {
        daoController.executeTransaction(() -> {
            CommonUser user = daoController.commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setEmail(newEmail);
            daoController.commonUserDAO.update(user);
        });
    }

    public void updateUserPassword(Long userId, String newPassword) {
        daoController.executeTransaction(() -> {
            CommonUser user = daoController.commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            user.setPassword(newPassword);
            daoController.commonUserDAO.update(user);
        });
    }

    public void deleteUser(Long userId) {
        daoController.executeTransaction(() -> {
            CommonUser user = daoController.commonUserDAO.findById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Usuário não encontrado.");
            }
            daoController.commonUserDAO.delete(user);
        });
    }
}