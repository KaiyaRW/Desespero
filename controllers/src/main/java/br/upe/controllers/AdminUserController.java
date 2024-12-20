package br.upe.controllers;

import br.upe.dao.AdminUserDAO;
import br.upe.pojos.AdminUser;
import br.upe.pojos.Event;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AdminUserController extends BaseController {
    private final AdminUserDAO adminUserDAO;

    public AdminUserController(EntityManager entityManager) {
        super(entityManager);
        this.adminUserDAO = new AdminUserDAO(entityManager);
    }

    public void updateAdminUserName(Long adminUserId, String newName) {
        executeTransaction(() -> {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setName(newName);
            adminUserDAO.update(adminUser);
        });
    }

    public void updateAdminUserEmail(Long adminUserId, String newEmail) {
        executeTransaction(() -> {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setEmail(newEmail);
            adminUserDAO.update(adminUser);
        });
    }

    public void updateAdminUserPassword(Long adminUserId, String newPassword) {
        executeTransaction(() -> {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setPassword(newPassword);
            adminUserDAO.update(adminUser);
        });
    }

    public void deleteAdminUser(Long adminUserId) {
        executeTransaction(() -> {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUserDAO.delete(adminUser);
        });
    }

    public List<Event> listAdminEvents(Long adminUserId) {
        return adminUserDAO.findById(adminUserId).getEvents();
    }
}