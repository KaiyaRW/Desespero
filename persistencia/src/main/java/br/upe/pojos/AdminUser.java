package br.upe.pojos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "admin_events")
    private List<Event> events = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "admin_submissions")
    private List<Event> submissions = new ArrayList<>();


    public AdminUser() {}

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}