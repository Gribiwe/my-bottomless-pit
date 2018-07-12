package com.vradiuse.dbManager.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Table(name = "point")
@Getter
@Setter
@NoArgsConstructor
public class Point {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "POINT_ID", nullable = false, updatable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person personId;

    @Column(name = "X", nullable = true, precision = 0)
    private Double x;

    @Column(name = "Y", nullable = true, precision = 0)
    private Double y;

    @Column(name = "RADIUS", nullable = true)
    private Integer radius;

    @Column(name = "CREATED", nullable = false)
    private Timestamp created;

}
