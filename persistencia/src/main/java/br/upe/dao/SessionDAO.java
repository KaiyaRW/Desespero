package br.upe.dao;

import br.upe.pojos.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class SessionDAO extends GenericDAO<Session> {

    public SessionDAO() {
        super(Session.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<Session> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT ge FROM Session ge "
                + "WHERE ge.startDate >= :startDate AND ge.endDate <= :endDate";
        TypedQuery<Session> query = entityManager.createQuery(jpql, Session.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

}
