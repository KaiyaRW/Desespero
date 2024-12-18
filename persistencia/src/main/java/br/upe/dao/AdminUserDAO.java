package br.upe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.upe.pojos.AdminUser;

public class AdminUserDAO extends GenericDAO<AdminUser> {
    public AdminUserDAO() {
        super(AdminUser.class);
    }

    @PersistenceContext
    private EntityManager entityManager;
    public List<AdminUser> findByEmail(String email) {
        String jpql = "SELECT au FROM AdminUser au WHERE au.email = :email";
        TypedQuery<AdminUser> query = entityManager.createQuery(jpql, AdminUser.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}