package database;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "point")
public class Point {

    Integer id;
    Person personId;
    Double x;
    Double y;
    Integer radius;
    Timestamp created;

    public Point() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "POINT_ID", nullable = false, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @Column(name = "X", nullable = true, precision = 0)
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Column(name = "Y", nullable = true, precision = 0)
    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Column(name = "RADIUS", nullable = true)
    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    @Column(name = "CREATED", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

}
