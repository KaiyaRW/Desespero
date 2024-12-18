package br.upe.controllers;

import br.upe.dao.SubscriptionDAO;
import br.upe.pojos.Subscription;

import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SubscriptionController {
    private final SubscriptionDAO subscriptionDAO;
    private final EntityManager entityManager;

    public SubscriptionController(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.subscriptionDAO = new SubscriptionDAO(entityManager);
    }

    /**
     * Realiza uma inscrição em um evento.
     *
     * @param userId ID do usuário que está se inscrevendo no evento.
     * @param eventId ID do evento em que o usuário está se inscrevendo.
     * @return A subscrição criada.
     */
    public Subscription subscribeToEvent(Long userId, Long eventId) {
        entityManager.getTransaction().begin();
        try {
            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setEventId(eventId);
            subscription.setDate(new Date()); // Define a data atual como data da inscrição

            subscriptionDAO.save(subscription); // Salva a inscrição no banco
            entityManager.getTransaction().commit();
            return subscription;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Realiza uma inscrição em uma sessão específica.
     *
     * @param userId ID do usuário que está se inscrevendo na sessão.
     * @param sessionId ID da sessão em que o usuário está se inscrevendo.
     * @param eventId ID do evento relacionado à sessão.
     * @return A subscrição criada.
     */
    public Subscription subscribeToSession(Long userId, Long sessionId, Long eventId) {
        entityManager.getTransaction().begin();
        try {
            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setSessionId(sessionId);
            subscription.setEventId(eventId);
            subscription.setDate(new Date()); // Define a data atual como data da inscrição

            subscriptionDAO.save(subscription); // Salva a inscrição no banco
            entityManager.getTransaction().commit();
            return subscription;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Remove uma inscrição relacionada a um evento pelo ID.
     *
     * @param subscriptionId ID da subscrição a ser removida.
     */
    public void removeEventSubscription(Long subscriptionId) {
        entityManager.getTransaction().begin();
        try {
            Subscription subscription = subscriptionDAO.findById(subscriptionId);
            if (subscription == null || subscription.getSessionId() != null) {
                throw new IllegalArgumentException("Inscrição em evento não encontrada ou é uma inscrição de sessão.");
            }
            subscriptionDAO.delete(subscription); // Remove a inscrição do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Remove uma inscrição relacionada a uma sessão pelo ID.
     *
     * @param subscriptionId ID da subscrição a ser removida.
     */
    public void removeSessionSubscription(Long subscriptionId) {
        entityManager.getTransaction().begin();
        try {
            Subscription subscription = subscriptionDAO.findById(subscriptionId);
            if (subscription == null || subscription.getSessionId() == null) {
                throw new IllegalArgumentException("Inscrição em sessão não encontrada ou é uma inscrição de evento.");
            }
            subscriptionDAO.delete(subscription); // Remove a inscrição do banco
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    /**
     * Lista todas as inscrições relacionadas a eventos.
     *
     * @return Lista de inscrições em eventos.
     */
    public List<Subscription> getAllEventSubscriptions() {
        try {
            List<Subscription> allSubscriptions = subscriptionDAO.findAll();
            // Filtra somente as inscrições relacionadas a eventos (sem sessionId)
            return allSubscriptions.stream()
                    .filter(subscription -> subscription.getSessionId() == null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar inscrições em eventos.", e);
        }
    }

    /**
     * Lista todas as inscrições relacionadas a sessões.
     *
     * @return Lista de inscrições em sessões.
     */
    public List<Subscription> getAllSessionSubscriptions() {
        try {
            List<Subscription> allSubscriptions = subscriptionDAO.findAll();
            // Filtra somente as inscrições relacionadas a sessões (com sessionId)
            return allSubscriptions.stream()
                    .filter(subscription -> subscription.getSessionId() != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar inscrições em sessões.", e);
        }
    }
}