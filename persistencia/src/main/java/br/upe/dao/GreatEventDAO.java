package br.upe.dao;

import br.upe.pojos.GreatEvent;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class GreatEventDAO extends GenericDAO<GreatEvent> {

    public GreatEventDAO(EntityManager entityManager) {
        super(GreatEvent.class, entityManager);
    }

    // Métodos específicos usam o EntityManager herdado do GenericDAO
    public List<GreatEvent> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT ge FROM GreatEvent ge "
                + "WHERE ge.startDate >= :startDate AND ge.endDate <= :endDate";
        TypedQuery<GreatEvent> query = entityManager.createQuery(jpql, GreatEvent.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}