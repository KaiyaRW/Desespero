package br.upe.facade;

import br.upe.controllers.*;
import br.upe.pojos.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

/**
 * Classe FacadeController refatorada para garantir que as responsabilidades sejam delegadas corretamente,
 * sem redundâncias entre os controladores.
 */
public class FacadeController {
    private final StateController stateController;
    private final AuthController authController;
    private final AdminUserController adminUserController;
    private final CommonUserController commonUserController;
    private final GreatEventController greatEventController;
    private final SessionController sessionController;
    private final SubscriptionController subscriptionController;

    public FacadeController() {
        // Inicialização dos controladores e dependências.
        this.stateController = new StateController();
        this.authController = new AuthController(stateController, new DAOController().adminUserDAO, new DAOController().commonUserDAO);
        this.adminUserController = new AdminUserController();
        this.commonUserController = new CommonUserController();
        this.greatEventController = new GreatEventController();
        this.sessionController = new SessionController();
        this.subscriptionController = new SubscriptionController();
    }

    // ------------------------ AUTENTICAÇÃO ------------------------

    /**
     * Realiza login de um usuário, seja administrador ou comum.
     */
    public void login(String email, String password) throws Exception {
        authController.login(email, password);
        User currentUser = stateController.getCurrentUser();

        if (currentUser.isAdmin()) {
            adminUserController.setCurrentAdminUser((AdminUser) currentUser);
        } else {
            commonUserController.setCurrentCommonUser((CommonUser) currentUser);
        }
    }

    /**
     * Realiza logout do sistema.
     */
    public void logout() {
        stateController.setCurrentUser(null);
        adminUserController.setCurrentAdminUser(null);
        commonUserController.setCurrentCommonUser(null);
    }

    // ------------------------ USUÁRIOS ------------------------

    public void createAdminUser(String name, String email, String password) throws Exception {
        authController.createNewAdmin(name, email, password);
    }

    public void createCommonUser(String name, String email, String password) throws Exception {
        authController.createNewUser(name, email, password);
    }

    public User getCurrentUser() {
        return stateController.getCurrentUser();
    }

    // ------------------------ GERENCIAR EVENTOS ------------------------

    public void createEvent(String descritor, String director) throws Exception {
        GreatEvent event = new GreatEvent();
        event.setDescritor(descritor);
        event.setDirector(director);

        adminUserController.addEvent(event);
    }

    public Collection<GreatEvent> getAdminEvents() {
        return adminUserController.getEvents();
    }

    public Collection<GreatEvent> getAvailableEvents() {
        return adminUserController.getEvents(); // Admin também acessa eventos (pode haver DAO específico).
    }

    // ------------------------ GERENCIAR INSCRIÇÕES ------------------------

    /**
     * Inscreve o usuário atual em um evento.
     */
    public void subscribeToEvent(Long eventId) throws Exception {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            throw new Exception("Usuário precisa estar autenticado.");
        }

        // Primeiro, tenta encontrar o evento dentro dos eventos administrados
        Optional<GreatEvent> event = adminUserController.getEvents().stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst();

        // Se não encontrar, busca diretamente pelo DAO
        if (event.isEmpty()) {
            DAOController daoController = new DAOController();
            event = Optional.ofNullable(daoController.greatEventDAO.findById(eventId));
        }

        if (event.isPresent()) {
            Subscription subscription = new Subscription();
            subscription.setUserId(currentUser.getId());
            subscription.setEventId(event.get().getId());
            subscription.setDate(new Date());

            subscriptionController.setCurrentSubscription(subscription);
            commonUserController.addSubscription(subscription);
        } else {
            throw new Exception("Evento não encontrado.");
        }
    }

    /**
     * Retorna todas as inscrições do usuário atual.
     */
    public Collection<Subscription> getUserSubscriptions() {
        return commonUserController.getSubscriptions();
    }
}