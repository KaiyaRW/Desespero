package br.upe.controllers;

import br.upe.pojos.User;

public class UserController {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void updateUserDetails(String name, String email, String password) {
        if (currentUser != null) {
            currentUser.setName(name);
            currentUser.setEmail(email);
            currentUser.setPassword(password);
        }
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin();
    }
}