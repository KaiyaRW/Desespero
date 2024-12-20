package br.upe.controllers;

import br.upe.pojos.AdminUser;
import br.upe.pojos.Event;

import java.util.List;

public class AdminUserController {

    private final DAOController daoController;

    public AdminUserController(DAOController daoController) {
        this.daoController = daoController;
    }

    public void updateAdminUserName(Long adminUserId, String newName) {
        daoController.executeTransaction(() -> {
            AdminUser adminUser = daoController.adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setName(newName);
            daoController.adminUserDAO.update(adminUser);
        });
    }

    public void updateAdminUserEmail(Long adminUserId, String newEmail) {
        daoController.executeTransaction(() -> {
            AdminUser adminUser = daoController.adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setEmail(newEmail);
            daoController.adminUserDAO.update(adminUser);
        });
    }

    public void updateAdminUserPassword(Long adminUserId, String newPassword) {
        daoController.executeTransaction(() -> {
            AdminUser adminUser = daoController.adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setPassword(newPassword);
            daoController.adminUserDAO.update(adminUser);
        });
    }

    public void deleteAdminUser(Long adminUserId) {
        daoController.executeTransaction(() -> {
            AdminUser adminUser = daoController.adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            daoController.adminUserDAO.delete(adminUser);
        });
    }

    public List<Event> listAdminEvents(Long adminUserId) {
        return daoController.executeTransactionWithReturn(() -> {
            AdminUser adminUser = daoController.adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            return adminUser.getEvents();
        });
    }
}