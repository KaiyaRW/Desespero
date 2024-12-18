package br.upe.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.upe.pojos.GreatEvent;

public class GreatEventDAO extends GenericDAO<GreatEvent> {
    public GreatEventDAO() {
        super(GreatEvent.class);
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<GreatEvent> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT ge FROM GreatEvent ge "
                + "WHERE ge.startDate >= :startDate AND ge.endDate <= :endDate";
        TypedQuery<GreatEvent> query = entityManager.createQuery(jpql, GreatEvent.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

}