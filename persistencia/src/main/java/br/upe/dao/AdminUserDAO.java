package br.upe.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import br.upe.pojos.AdminUser;

public class AdminUserDAO extends GenericDAO<AdminUser> {

    private final EntityManager entityManager;

    // Construtor que recebe o EntityManager
    public AdminUserDAO(EntityManager entityManager) {
        super(AdminUser.class, entityManager); // Passa o EntityManager para a superclasse
        this.entityManager = entityManager;
    }

    public List<AdminUser> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio.");
        }

        String jpql = "SELECT au FROM AdminUser au WHERE au.email = :email";
        TypedQuery<AdminUser> query = entityManager.createQuery(jpql, AdminUser.class);
        query.setParameter("email", email); // Define o parâmetro da query
        return query.getResultList();
    }
}