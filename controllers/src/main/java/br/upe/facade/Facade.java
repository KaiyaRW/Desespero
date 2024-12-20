package br.upe.facade;

import br.upe.controllers.*;

public class Facade {
    private final AdminUserController adminUserController;
    private final CommonUserController commonUserController;
    private final EventController eventController;
    private final SessionController sessionController;
    private final SubscriptionController subscriptionController;
    private final SubmissionController submissionController;
    private final AuthController authController;
    private final StateController stateController;
    private final DAOController daoController;

    /**
     * Inicializa o Facade centralizando o DAOController e os controladores.
     */
    public Facade() {
        // Instancia o DAOController
        this.daoController = new DAOController();

        // Instancia os controllers necessários
        this.stateController = new StateController();
        this.adminUserController = new AdminUserController(daoController);
        this.commonUserController = new CommonUserController(daoController);
        this.eventController = new EventController(daoController);
        this.sessionController = new SessionController(daoController);
        this.subscriptionController = new SubscriptionController(daoController);
        this.submissionController = new SubmissionController(daoController);

        // Passa o DAOController diretamente para o AuthController
        this.authController = new AuthController(stateController, daoController);
    }

    /**
     * Fecha o DAOController e todos os controladores, liberando recursos.
     */
    public void close() {
        daoController.close();
    }

    // Getters para acesso aos controladores
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