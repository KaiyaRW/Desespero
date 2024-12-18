package br.upe.controllers;

import br.upe.dao.GreatEventDAO;
import br.upe.pojos.GreatEvent;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class GreatEventController {
    private final GreatEventDAO greatEventDAO;
    private final EntityManager entityManager;

    public GreatEventController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.greatEventDAO = new GreatEventDAO(entityManager);
    }

    /**
     * Cria um novo evento.
     *
     * @param titulo Título do evento.
     * @param descritor Descrição do evento.
     * @param director Nome do diretor do evento.
     * @param startDate Data de início do evento.
     * @param endDate Data de término do evento.
     * @return O evento criado.
     */
    public GreatEvent createEvent(String titulo, String descritor, String director, Date startDate, Date endDate) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = new GreatEvent();
            event.setTitulo(titulo);
            event.setDescritor(descritor);
            event.setDirector(director);
            event.setStartDate(startDate);
            event.setEndDate(endDate);

            greatEventDAO.save(event); // Salva o evento no banco
            entityManager.getTransaction().commit();
            return event;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza o título de um evento.
     *
     * @param eventId ID do evento a ser atualizado.
     * @param newTitulo Novo título do evento.
     */
    public void updateEventTitulo(Long eventId, String newTitulo) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setTitulo(newTitulo);
            greatEventDAO.update(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a descrição de um evento.
     *
     * @param eventId ID do evento a ser atualizado.
     * @param newDescritor Nova descrição do evento.
     */
    public void updateEventDescritor(Long eventId, String newDescritor) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setDescritor(newDescritor);
            greatEventDAO.update(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza o diretor de um evento.
     *
     * @param eventId ID do evento a ser atualizado.
     * @param newDirector Novo nome do diretor.
     */
    public void updateEventDirector(Long eventId, String newDirector) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setDirector(newDirector);
            greatEventDAO.update(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a data de início de um evento.
     *
     * @param eventId ID do evento a ser atualizado.
     * @param newStartDate Nova data de início.
     */
    public void updateEventStartDate(Long eventId, Date newStartDate) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setStartDate(newStartDate);
            greatEventDAO.update(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Atualiza a data de término de um evento.
     *
     * @param eventId ID do evento a ser atualizado.
     * @param newEndDate Nova data de término.
     */
    public void updateEventEndDate(Long eventId, Date newEndDate) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            event.setEndDate(newEndDate);
            greatEventDAO.update(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Lista todos os eventos.
     *
     * @return Lista contendo todos os eventos cadastrados.
     */
    public List<GreatEvent> getAllEvents() {
        try {
            return greatEventDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar eventos.", e);
        }
    }

    /**
     * Remove um evento pelo ID.
     *
     * @param eventId ID do evento a ser removido.
     */
    public void deleteEvent(Long eventId) {
        entityManager.getTransaction().begin();
        try {
            GreatEvent event = greatEventDAO.findById(eventId);
            if (event == null) {
                throw new IllegalArgumentException("Evento não encontrado.");
            }
            greatEventDAO.delete(event);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}