package br.upe.controllers;

import br.upe.pojos.AdminUser;
import br.upe.pojos.CommonUser;
import br.upe.pojos.User;

public class AuthController {

    private final StateController stateController;
    private final DAOController daoController;

    public AuthController(StateController stateController, DAOController daoController) {
        this.stateController = stateController;
        this.daoController = daoController;
    }

    public void createNewUser(String name, String email, String password) {
        daoController.executeTransaction(() -> {
            CommonUser existingUser = daoController.commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            AdminUser existingAdmin = daoController.adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

            if (existingUser != null || existingAdmin != null) {
                throw new RuntimeException("Usuário já existe.");
            }

            CommonUser newUser = new CommonUser();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setPassword(password);

            daoController.commonUserDAO.save(newUser);
        });
    }

    public void createNewAdmin(String name, String email, String password) {
        daoController.executeTransaction(() -> {
            CommonUser existingUser = daoController.commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            AdminUser existingAdmin = daoController.adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

            if (existingUser != null || existingAdmin != null) {
                throw new RuntimeException("Já existe um usuário ou administrador com este e-mail.");
            }

            AdminUser newAdmin = new AdminUser();
            newAdmin.setName(name);
            newAdmin.setEmail(email);
            newAdmin.setPassword(password);

            daoController.adminUserDAO.save(newAdmin);
        });
    }

    public void login(String email, String password) throws Exception {
        daoController.executeTransaction(() -> {
            User user = null;
            AdminUser adminUser = daoController.adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);
            if (adminUser != null) {
                user = adminUser;
            } else {
                CommonUser commonUser = daoController.commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
                if (commonUser != null) {
                    user = commonUser;
                }
            }

            if (user == null || !user.getPassword().equals(password)) {
                try {
                    throw new Exception("Login inválido!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            stateController.setCurrentUser(user);
        });
    }

    public void logout() {
        stateController.setCurrentUser(null);
    }
}