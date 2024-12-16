package br.upe.pojos;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "common_users")
@Inheritance(strategy = InheritanceType.JOINED)  // Optional for single table inheritance
public class CommomUser extends User {

    protected final boolean isAdmin = false;

    @OneToMany(mappedBy = "user")  // OneToMany with user
    private Collection<Subscription> subscriptions;
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
    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Collection<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
    public boolean isAdmin() { return isAdmin; }
}
