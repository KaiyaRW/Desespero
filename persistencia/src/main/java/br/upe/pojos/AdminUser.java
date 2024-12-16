package br.upe.pojos;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUser extends User {

    @OneToMany(mappedBy = "user")
    private Collection<Subscription> subscriptions;

    @ManyToMany
    @JoinColumn(name = "admin_uuid", nullable = false)  // Foreign key for AdminUser
    private Collection<GreatEvent> events;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }
    public void setId(Long id) {
        this.setId(id);
    }
    public Long getId() {
        return this.getId();
    }
    public void addSubscription(Subscription subscription){
        subscriptions.add(subscription);
    }

    public Collection<GreatEvent> getEvents(){
        return events;
    }
    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Collection<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setEvents(Collection<GreatEvent> events){this.events = events;}
    public boolean isAdmin() {
        return isAdmin;
    }

    public void addEvent(GreatEvent event) {this.events.add(event);}

}
