package br.upe.controllers;

import br.upe.pojos.AdminUser;
import br.upe.pojos.GreatEvent;

import java.util.Collection;
import java.util.Collections;

public class AdminUserController {
    private AdminUser currentAdminUser;

    public AdminUser getCurrentAdminUser() {
        return currentAdminUser;
    }

    public void setCurrentAdminUser(AdminUser adminUser) {
        this.currentAdminUser = adminUser;
    }

    public Collection<GreatEvent> getEvents() {
        return currentAdminUser != null ? currentAdminUser.getEvents() : Collections.emptyList(); // Retorna vazio
    }

    public void addEvent(GreatEvent event) {
        if (currentAdminUser != null) {
            currentAdminUser.addEvent(event);
        }
    }

    public boolean isAdmin() {
        return currentAdminUser != null && currentAdminUser.isAdmin();
    }
}