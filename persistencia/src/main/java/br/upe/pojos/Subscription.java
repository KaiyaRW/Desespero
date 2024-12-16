package br.upe.pojos;

import java.util.Date;
import java.util.UUID;

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

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "session_uuid")
    private UUID sessionUuid;

    @Column(name = "subscription_date")
    private Date date;

    public Long getId() {
        return this.id;
    }
    public UUID getSessionUuid() {
        return sessionUuid;
    }
    public UUID getUserUuid() {
        return userUuid;
    }
    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setSessionUuid(UUID sessionUuid) {
        this.sessionUuid = sessionUuid;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }

}
