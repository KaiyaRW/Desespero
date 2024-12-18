package br.upe.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Use Long instead of UUID for database ID

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "session_id")
    private Long sessionId;

    @Column(name = "subscription_date")
    private Date date;

    public Long getId() {
        return this.id;
    }
    public Long getSessionId() {
        return sessionId;
    }
    public Long getUserId() {return userId; }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

}
