package br.upe.pojos;

import java.util.Date;
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
public class Submission extends GreatEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Use Long instead of UUID for database ID

    @Column(name = "user_Id")
    private Long userId;

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
    public Long getEventId() { return getEventId(); }
    public void setEventId(Long eventId) {
        this.setEventId(eventId);
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public Long getUserId(){
        return userId;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
}
