package br.upe.controllers;

import br.upe.dao.AdminUserDAO;
import br.upe.pojos.AdminUser;
import br.upe.pojos.GreatEvent;

import jakarta.persistence.EntityManager;

import java.util.List;

public class AdminUserController {
    private final AdminUserDAO adminUserDAO;
    private final EntityManager entityManager;

    public AdminUserController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.adminUserDAO = new AdminUserDAO(entityManager);
    }

    /**
     * Atualiza o nome do usuário administrador.
     *
     * @param adminUserId ID do administrador.
     * @param newName Novo nome a ser atribuído.
     */
    public void updateAdminUserName(Long adminUserId, String newName) {
        entityManager.getTransaction().begin();
        try {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setName(newName);
            adminUserDAO.update(adminUser); // Atualiza o administrador no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza o e-mail do usuário administrador.
     *
     * @param adminUserId ID do administrador.
     * @param newEmail Novo e-mail a ser atribuído.
     */
    public void updateAdminUserEmail(Long adminUserId, String newEmail) {
        entityManager.getTransaction().begin();
        try {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setEmail(newEmail);
            adminUserDAO.update(adminUser); // Atualiza o administrador no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a senha do usuário administrador.
     *
     * @param adminUserId ID do administrador.
     * @param newPassword Nova senha a ser atribuída.
     */
    public void updateAdminUserPassword(Long adminUserId, String newPassword) {
        entityManager.getTransaction().begin();
        try {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUser.setPassword(newPassword);
            adminUserDAO.update(adminUser); // Atualiza o administrador no banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Remove o usuário administrador pelo ID.
     *
     * @param adminUserId ID do administrador a ser removido.
     */
    public void deleteAdminUser(Long adminUserId) {
        entityManager.getTransaction().begin();
        try {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            adminUserDAO.delete(adminUser); // Remove o administrador do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Lista todos os eventos associados a um administrador.
     *
     * @param adminUserId ID do administrador.
     * @return Lista de eventos associados ao administrador.
     */
    public List<GreatEvent> listAdminEvents(Long adminUserId) {
        try {
            AdminUser adminUser = adminUserDAO.findById(adminUserId);
            if (adminUser == null) {
                throw new IllegalArgumentException("Usuário administrador não encontrado.");
            }
            return adminUser.getEvents(); // Retorna a lista de eventos associados ao administrador
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar os eventos do administrador.", e);
        }
    }
}