package com.vradiuse.dbManager.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "channel")
@Getter
@Setter
@NoArgsConstructor
public class Channel {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "CHANNEL_ID", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER_ID")
    private Person owner;

    @Column(name = "NAME", nullable = true, length = 256)
    private String name;

    @Column(name = "size", nullable = true)
    private Integer size;

    @Column(name = "type", nullable = true, length = 20)
    private String type;

    @Column(name = "CREATED", nullable = false)
    private Timestamp created;

    @Column(name = "EXPIRED", nullable = false)
    private Timestamp expired;

    @ManyToMany
    @JoinTable(name = "channel_to_person",
            joinColumns = @JoinColumn(name = "CHANNEL_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
    private Set<Person> personsSet = new HashSet<>();//CHANNEL_TO_PERSON

}
