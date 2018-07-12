package com.vradiuse.dbManager.database;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
public class Message {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "MESSAGE_ID", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHANNEL_ID")
    private Channel channelId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person personId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTENT_ID")
    private Content content;

    @Column(name = "CREATED", nullable = false)
    private Timestamp created;

    @Column(name = "EXPIRED", nullable = false)
    private Timestamp expired;

}
