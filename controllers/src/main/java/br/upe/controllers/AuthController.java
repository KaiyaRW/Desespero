package br.upe.controllers;

import br.upe.pojos.AdminUser;
import br.upe.pojos.CommonUser;
import br.upe.pojos.User;
import br.upe.dao.AdminUserDAO;
import br.upe.dao.CommonUserDAO;

public class AuthController {
    private final StateController stateController;
    private final AdminUserDAO adminUserDAO;
    private final CommonUserDAO commonUserDAO;

    public AuthController(StateController stateController, AdminUserDAO adminUserDAO, CommonUserDAO commonUserDAO) {
        this.stateController = stateController;
        this.adminUserDAO = adminUserDAO;
        this.commonUserDAO = commonUserDAO;
    }

    /**
     * Cria um novo usuário comum.
     */
    public void createNewUser(String name, String surname, String cpf, String email, String password) throws Exception {
        // Verifica se já existe um usuário ou admin com o mesmo e-mail
        CommonUser existingUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
        AdminUser existingAdmin = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

        if (existingUser != null || existingAdmin != null) {
            throw new Exception("Um usuário ou administrador com o e-mail " + email + " já existe.");
        }

        // Cria um novo usuário comum
        CommonUser newUser = new CommonUser();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        // Salvar o novo usuário
        commonUserDAO.save(newUser);
    }

    /**
     * Cria um novo administrador.
     */
    public void createNewAdmin(String name, String surname, String cpf, String email, String password) throws Exception {
        // Verifica se já existe um usuário ou admin com o mesmo e-mail
        CommonUser existingUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);
        AdminUser existingAdmin = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

        if (existingUser != null || existingAdmin != null) {
            throw new Exception("Um usuário ou administrador com o e-mail " + email + " já existe.");
        }

        // Cria um novo administrador
        AdminUser newAdmin = new AdminUser();
        newAdmin.setName(name);
        newAdmin.setEmail(email);
        newAdmin.setPassword(password);

        // Salvar o novo administrador
        adminUserDAO.save(newAdmin);
    }

    /**
     * Realiza o login de um usuário ou administrador.
     */
    public void login(String email, String password) throws Exception {
        User user = null;

        // Tenta encontrar o usuário no DAO de Administradores
        AdminUser adminUser = adminUserDAO.findByEmail(email).stream().findFirst().orElse(null);

        // Se não for admin, buscar no DAO de Usuários Comuns
        if (adminUser != null) {
            user = adminUser;
        } else {
            CommonUser commonUser = commonUserDAO.findByEmail(email).stream().findFirst().orElse(null);

            if (commonUser != null) {
                user = commonUser;
            }
        }

        // Se não encontrado, lançar exceção
        if (user == null) {
            throw new Exception("Usuário com e-mail " + email + " não foi encontrado.");
        }

        // Verificação da senha
        if (!user.getPassword().equals(password)) {
            throw new Exception("Senha incorreta para o usuário " + email);
        }

        // Define o usuário autenticado no controlador de estado
        stateController.setCurrentUser(user);
    }

    /**
     * Realiza o logout do sistema.
     */
    public void logout() {
        stateController.setCurrentUser(null);
    }
}