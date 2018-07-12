package com.vradiuse.dbManager.database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "content")
@Getter
@Setter
@NoArgsConstructor
public class Content {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Column(name = "CONTENT_ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "TEXT", nullable = true, length = 300)
    private String text=null;

    @Column(name = "SOUND_URL", nullable = true, length = 300)
    private String soundUrl=null;

    @Column(name = "VIDEO_URL", nullable = true, length = 300)
    private String videoUrl=null;

    @Column(name = "IMG_URL", nullable = true, length = 300)
    private String imgUrl=null;

}
