package br.upe.controllers;

import br.upe.dao.EventDAO;
import br.upe.pojos.Event;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class EventController extends BaseController {

    private final EventDAO eventDAO;

    public EventController(EntityManager entityManager) {
        super(entityManager);
        this.eventDAO = new EventDAO(entityManager);
    }

    public Event createEvent(String titulo, String descritor, String director, Date startDate, Date endDate) {
        return executeTransactionWithReturn(() -> {
            Event event = new Event();
            event.setTitulo(titulo);
            event.setDescritor(descritor);
            event.setDirector(director);
            event.setStartDate(startDate);
            event.setEndDate(endDate);

            eventDAO.save(event);
            return event;
        });
    }

    public void updateEventTitulo(Long eventId, String newTitulo) {
        executeTransaction(() -> {
            Event event = eventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setTitulo(newTitulo);
            eventDAO.update(event);
        });
    }

    public void updateEventDescritor(Long eventId, String newDescritor) {
        executeTransaction(() -> {
            Event event = eventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setDescritor(newDescritor);
            eventDAO.update(event);
        });
    }

    public List<Event> getAllEvents() {
        return executeTransactionWithReturn(eventDAO::findAll);
    }

    public void deleteEvent(Long eventId) {
        executeTransaction(() -> {
            Event event = eventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            eventDAO.delete(event);
        });
    }
}