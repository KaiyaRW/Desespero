package br.upe.controllers;

import br.upe.pojos.CommonUser;
import br.upe.pojos.Subscription;

import java.util.Collection;

public class CommonUserController {
    private CommonUser currentCommonUser;

    public CommonUser getCurrentCommonUser() {
        return currentCommonUser;
    }

    public void setCurrentCommonUser(CommonUser commonUser) {
        this.currentCommonUser = commonUser;
    }

    public Collection<Subscription> getSubscriptions() {
        return currentCommonUser != null ? currentCommonUser.getSubscriptions() : null;
    }

    public void addSubscription(Subscription subscription) {
        if (currentCommonUser != null) {
            currentCommonUser.addSubscription(subscription);
        }
    }

    public boolean isAdmin() {
        return false;
    }
}