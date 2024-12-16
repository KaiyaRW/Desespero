package br.upe.pojos;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class EventComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long eventUuid;

    @Column(nullable = false)
    private String descritor;
    public abstract Long getId();
    public abstract void setId(Long id);
    public abstract UUID getEventUuid();
    public abstract void setEventUuid(UUID eventUuid);
    public abstract void setDescritor(String descritor);
    public abstract String getDescritor();

}
