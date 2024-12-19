package br.upe.pojos;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Relacionamento com User
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Relacionamento com Event
    private Event event;

    @ManyToOne
    @JoinColumn(name = "session_id") // Relacionamento com Session (opcional)
    private Session session;

    @Column(name = "subscription_date", nullable = false)
    private Date date;

    public Subscription() {}

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}