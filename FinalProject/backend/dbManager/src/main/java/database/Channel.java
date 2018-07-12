package database;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "session")
public class Channel {

    private Integer id;
    private Person owner;
    private String name;
    private Integer size;
    private String type;
    private Timestamp created;
    private Timestamp expired;

    public Channel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CHANNEL_ID", nullable = false, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person personId) {
        this.owner = personId;
    }

    @Column(name = "NAME", nullable = true, length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "size", nullable = true)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "CREATED", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Column(name = "EXPIRED", nullable = false)
    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    private Set<Person> personsSet = new HashSet<>();//CHANNEL_TO_PERSON

    @ManyToMany
    @JoinTable(name = "channel_to_person",
            joinColumns = @JoinColumn(name = "CHANNEL_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
    public Set<Person> getPersonsSet() {
        return personsSet;
    }

    public void setPersonsSet(Set<Person> personsSet) {
        this.personsSet = personsSet;
    }
}
