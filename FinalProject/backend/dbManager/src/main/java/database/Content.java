package database;

import javax.persistence.*;

@Entity
@Table(name = "content")
public class Content {
    private Integer id;
    private String text;
    private String soundUrl;
    private String videoUrl;
    private String imgUrl;

    public Content() {
        this.text = null;
        this.soundUrl = null;
        this.videoUrl = null;
        this.imgUrl = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CONTENT_ID", nullable = false, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "TEXT", nullable = true, length = 300)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "SOUND_URL", nullable = true, length = 300)
    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    @Column(name = "VIDEO_URL", nullable = true, length = 300)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(name = "IMG_URL", nullable = true, length = 300)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
