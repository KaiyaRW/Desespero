package br.upe.pojos;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session extends EventComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Use Long instead of UUID for database ID

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)  // OneToMany with cascade
    private Collection<Subscription> subscriptions;

    public void setDescritor(String descritor) {
        this.setDescritor(descritor);
    }
    public String getDescritor(){
        return this.getDescritor();
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
    public void setId(Long id) {
        this.setId(id);
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
    public Date getStartDate() {
        return this.startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
