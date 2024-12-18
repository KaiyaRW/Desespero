package br.upe.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "common_users")
public class CommonUser extends User {

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions = new ArrayList<>();

    public CommonUser() {}

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}