package com.vradiuse.dbManager.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;


@Entity
@Table(name = "session")
@Getter
@Setter
@NoArgsConstructor
public class Session {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "SESSION_ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "CREATED", nullable = false)
    private Timestamp started;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private Person personId;

    @Column(name = "STATUS", nullable = false)
    private boolean status;

}
