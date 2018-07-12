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
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "PERSON_ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "LOGIN", length = 80)
    private String login;//email

    @Column(name = "NICK", length = 15)
    private String nick;

    @Column(name = "PASSWORD", length = 50)
    private String password;

    @Column(name = "AVATAR", length = 50)
    private String avatar;

    @Column(name = "CREATED")
    private Timestamp created;

    @Column(name = "EXPIRED")
    private Timestamp expired;

    @ManyToMany
    @JoinTable(name = "channel_to_person",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHANNEL_ID"))
    private Set<Channel> channelSet = new HashSet<>();//CHANNEL_TO_PERSON

}
