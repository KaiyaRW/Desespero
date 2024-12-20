package br.upe.controllers;

import br.upe.pojos.Event;

import java.util.Date;
import java.util.List;

public class EventController {

    private final DAOController daoController;

    public EventController(DAOController daoController) {
        this.daoController = daoController;
    }

    public Event createEvent(String titulo, String descritor, String director, Date startDate, Date endDate) {
        return daoController.executeTransactionWithReturn(() -> {
            Event event = new Event();
            event.setTitulo(titulo);
            event.setDescritor(descritor);
            event.setDirector(director);
            event.setStartDate(startDate);
            event.setEndDate(endDate);

            daoController.eventDAO.save(event);
            return event;
        });
    }

    public List<Event> getAllEvents() {
        return daoController.eventDAO.findAll();
    }
}