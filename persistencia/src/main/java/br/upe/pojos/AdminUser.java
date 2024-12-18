package br.upe.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUser extends User {

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "admin_events")
    private List<GreatEvent> events = new ArrayList<>();

    public AdminUser() {}

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<GreatEvent> getEvents() {
        return events;
    }

    public void setEvents(List<GreatEvent> events) {
        this.events = events;
    }

    public void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }

    public void addEvent(GreatEvent event) {
        this.events.add(event);
    }
}