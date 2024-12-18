package br.upe.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import br.upe.pojos.CommonUser;

public class CommonUserDAO extends GenericDAO<CommonUser> {

    private final EntityManager entityManager;

    // Construtor que recebe o EntityManager
    public CommonUserDAO(EntityManager entityManager) {
        super(CommonUser.class, entityManager); // Passa o EntityManager para a superclasse
        this.entityManager = entityManager;
    }

    public List<CommonUser> findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser nulo ou vazio.");
        }

        String jpql = "SELECT cu FROM CommonUser cu WHERE cu.email = :email";
        TypedQuery<CommonUser> query = entityManager.createQuery(jpql, CommonUser.class);
        query.setParameter("email", email); // Define o parâmetro da query
        return query.getResultList();
    }
}