package br.upe.pojos;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "submission_date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false) // Define o relacionamento com 'event'
    private Event event;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "link", nullable = false)
    private String link;

    public Submission() {}

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Event getEvent() { // Nome ajustado para 'Event'
        return event;
    }

    public void setEvent(Event event) { // Nome ajustado para 'Event'
        this.event = event;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}