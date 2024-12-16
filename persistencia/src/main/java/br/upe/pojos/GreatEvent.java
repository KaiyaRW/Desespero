package br.upe.pojos;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "great_events")
public class GreatEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Use Long instead of UUID for database ID

    @Column(name = "descritor")
    private String descritor;

    @Column(name = "director")
    private String director;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany(mappedBy = "greatEvent", cascade = CascadeType.ALL)  // OneToMany with cascade
    private Collection<Session> sessions;

    @OneToMany(mappedBy = "greatEvent", cascade = CascadeType.ALL)  // OneToMany with cascade
    private Collection<Submission> submissions;


    public Collection<Submission> getSubmissions() {
        return submissions;
    }
    public void setSubmissions(Collection<Submission> submissions) {
        this.submissions = submissions;
    }
    public void addSubmission(Submission submission) {
        this.submissions.add(submission);
    }
    public void setDescritor(String descritor) {
        this.descritor = descritor;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getDescritor() {
        return this.descritor;
    }
    public String getDirector() {
        return this.director;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    public Collection<Session> getSessions(){
        return this.sessions;
    }
    public void setSessions(Collection<Session> sessions){
        this.sessions = sessions;
    }
    public void addSession(Session session){
        this.sessions.add(session);
    }
}
