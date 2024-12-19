package br.upe.facade;

import br.upe.controllers.*;
import jakarta.persistence.EntityManager;

public class Facade {
    private final AdminUserController adminUserController;
    private final CommonUserController commonUserController;
    private final EventController eventController;
    private final SessionController sessionController;
    private final SubscriptionController subscriptionController;
    private final SubmissionController submissionController;
    private final AuthController authController;
    private final StateController stateController;

    /**
     * Facade que centraliza acesso aos controladores da aplicação.
     *
     * @param entityManager EntityManager usado em todos controladores.
     */
    public Facade(EntityManager entityManager) {
        // Instancia os controllers necessários
        this.stateController = new StateController();
        this.adminUserController = new AdminUserController(entityManager);
        this.commonUserController = new CommonUserController(entityManager);
        this.eventController = new EventController(entityManager);
        this.sessionController = new SessionController(entityManager);
        this.subscriptionController = new SubscriptionController(entityManager);
        this.submissionController = new SubmissionController(entityManager);

        // Passa os DAOs necessários para o AuthController
        this.authController = new AuthController(
                stateController,
                new DAOController().adminUserDAO,
                new DAOController().commonUserDAO
        );
    }

    // Métodos de acesso aos controladores

    public AdminUserController getAdminUserController() {
        return adminUserController;
    }

    public CommonUserController getCommonUserController() {
        return commonUserController;
    }

    public EventController getEventController() {
        return eventController;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public SubscriptionController getSubscriptionController() {
        return subscriptionController;
    }

    public SubmissionController getSubmissionController() {
        return submissionController;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public StateController getStateController() {
        return stateController;
    }
}