package br.upe.dao;

import br.upe.pojos.Session;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class SessionDAO extends GenericDAO<Session> {

    public SessionDAO(EntityManager entityManager) {
        super(Session.class, entityManager);
    }

    // Métodos específicos usam o EntityManager herdado do GenericDAO
    public List<Session> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT s FROM Session s "
                + "WHERE s.startDate >= :startDate AND s.endDate <= :endDate";
        TypedQuery<Session> query = entityManager.createQuery(jpql, Session.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}