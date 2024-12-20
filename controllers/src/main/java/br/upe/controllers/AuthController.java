package br.upe.controllers;

import br.upe.dao.AdminUserDAO;
import br.upe.dao.CommonUserDAO;
import br.upe.pojos.AdminUser;
import br.upe.pojos.CommonUser;
import br.upe.pojos.User;

import jakarta.persistence.EntityManager;

public class AuthController extends BaseController {

    private final StateController stateController;
    private final AdminUserDAO adminUserDAO;
    private final CommonUserDAO commonUserDAO;

    public AuthController(StateController stateController, EntityManager entityManager) {
        super(entityManager);
        this.stateController = stateController;
        this.adminUserDAO = new AdminUserDAO(entityManager);
        this.commonUserDAO = new CommonUserDAO(entityManager);
    }

    public void createNewUser(String name, String email, String password) {
        executeTransaction(() -> {
            CommonUser existingUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            AdminUser existingAdmin = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

            if (existingUser != null || existingAdmin != null) {
                throw new RuntimeException("Usuário já existe.");
            }

            CommonUser newUser = new CommonUser();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password);

            commonUserDAO.save(newUser);
        });
    }

    public void createNewAdmin(String name, String email, String password) {
        executeTransaction(() -> {
            CommonUser existingUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            AdminUser existingAdmin = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

            if (existingUser != null || existingAdmin != null) {
                throw new RuntimeException("Já existe um usuário ou administrador com este e-mail.");
            }

            AdminUser newAdmin = new AdminUser();
            newAdmin.setName(name);
            newAdmin.setEmail(email);
            newAdmin.setPassword(password);

            adminUserDAO.save(newAdmin);
        });
    }

    public void login(String email, String password) throws Exception {
        User user = null;

        AdminUser adminUser = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);
        if (adminUser != null) {
            user = adminUser;
        } else {
            CommonUser commonUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            if (commonUser != null) {
                user = commonUser;
            }
        }

        if (user == null) {
            throw new Exception("Usuário não encontrado.");
        }

        if (!user.getPassword().equals(password)) {
            throw new Exception("Senha incorreta.");
        }

        stateController.setCurrentUser(user);
    }

    public void logout() {
        stateController.setCurrentUser(null);
    }
}