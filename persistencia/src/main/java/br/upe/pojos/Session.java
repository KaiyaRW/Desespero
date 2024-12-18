package br.upe.pojos;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descritor", nullable = false)
    private String descritor;

    @ManyToOne // Relacionamento Many-To-One com GreatEvent
    @JoinColumn(name = "great_event_id", nullable = false) // Nome da FK na tabela "session"
    private GreatEvent greatEvent;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescritor() {
        return descritor;
    }

    public void setDescritor(String descritor) {
        this.descritor = descritor;
    }

    public GreatEvent getGreatEvent() {
        return greatEvent;
    }

    public void setGreatEvent(GreatEvent greatEvent) {
        this.greatEvent = greatEvent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}