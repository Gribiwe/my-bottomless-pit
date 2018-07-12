package database;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "session")
public class Session {
    private Integer id;
    private Timestamp started;
    private Person personId;
    private boolean status;

    public Session() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SESSION_ID", nullable = false, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CREATED", nullable = false)
    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp timestamp) {
        this.started = timestamp;
    }

    @Column(name = "STATUS", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }
}
