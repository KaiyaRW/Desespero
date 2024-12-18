package br.upe.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.upe.pojos.CommonUser;
import br.upe.pojos.Submission;

public class CommonUserDAO extends GenericDAO<CommonUser> {

    public CommonUserDAO() {
        super(CommonUser.class);
    }
    @PersistenceContext
    private EntityManager entityManager;

    public List<CommonUser> findByEmail(String email) {
        String jpql = "SELECT cu FROM CommonUser cu WHERE cu.email = :email";
        TypedQuery<CommonUser> query = entityManager.createQuery(jpql, CommonUser.class);
        query.setParameter("email", email);
        return query.getResultList();
    }
}