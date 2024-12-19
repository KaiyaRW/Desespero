package br.upe.controllers;

import br.upe.dao.AdminUserDAO;
import br.upe.pojos.AdminUser;
import br.upe.pojos.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AdminUserController {
    private final AdminUserDAO adminUserDAO;
    private final EntityManager entityManager;

    public AdminUserController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.adminUserDAO = new AdminUserDAO(entityManager);
    }

    private void executeTransaction(Runnable action) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            action.run(); // Executa o código encapsulado na action
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
        return adminUserDAO.findById(adminUserId).getEvents(); // Consulta eventos do admin
    }
}