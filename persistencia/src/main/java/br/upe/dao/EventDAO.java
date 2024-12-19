package br.upe.dao;

import br.upe.pojos.Event;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class EventDAO extends GenericDAO<Event> {

    public EventDAO(EntityManager entityManager) {
        super(Event.class, entityManager);
    }

    // Métodos específicos usam o EntityManager herdado do GenericDAO
    public List<Event> findByDateRange(Date startDate, Date endDate) {
        String jpql = "SELECT ge FROM Event ge "
                + "WHERE ge.startDate >= :startDate AND ge.endDate <= :endDate";
        TypedQuery<Event> query = entityManager.createQuery(jpql, Event.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}