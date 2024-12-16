package br.upe.pojos;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "submissions")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // Optional for single table inheritance
public class Submission extends EventComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Use Long instead of UUID for database ID

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "submission_date")
    private Date date;

    public void setDescritor(String descritor) {
        this.setDescritor(descritor);
    }
    public String getDescritor(){
        return this.getDescritor();
    }
    public void setId(Long id) {
        this.setId(this.id);
    }
    public Long getId() {
        return this.getId();
    }
    public UUID getEventUuid() {
        return getEventUuid();
    }

    public void setEventUuid(UUID eventUuid) {
        this.setEventUuid(eventUuid);
    }
    public void setUserUuid(UUID userUuid){
        this.userUuid = userUuid;
    }
    public UUID getUserUuid(){
        return userUuid;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
}
