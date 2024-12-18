package br.upe.controllers;

import br.upe.dao.*;

public class DAOController {
    public final GreatEventDAO greatEventDAO;
    public final SessionDAO sessionDAO;
    public final SubmissionDAO submissionDAO;
    public final SubscriptionDAO subscriptionDAO;
    public final AdminUserDAO adminUserDAO;
    public final CommonUserDAO commonUserDAO;

    public DAOController() {
        greatEventDAO = new GreatEventDAO();
        sessionDAO = new SessionDAO();
        submissionDAO = new SubmissionDAO();
        subscriptionDAO = new SubscriptionDAO();
        adminUserDAO = new AdminUserDAO();
        commonUserDAO = new CommonUserDAO();
    }

}
