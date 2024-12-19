package br.upe.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("COMMON")
public class CommonUser extends User {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions = new ArrayList<>();

    public CommonUser() {}

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    // Adiciona uma nova Subscription ao usuário (relação bidirecional)
    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
        subscription.setUser(this);
    }

    // Remove uma Subscription do usuário (relação bidirecional)
    public void removeSubscription(Subscription subscription) {
        subscriptions.remove(subscription);
        subscription.setUser(null); // Remove a referência ao usuário
    }
}