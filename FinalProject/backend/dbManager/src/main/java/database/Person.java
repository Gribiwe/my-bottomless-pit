package database;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "person")
public class Person {

    private Integer id;
    private String login;//email
    private String nick;
    private String password;
    private String avatar;
    private Timestamp created;
    private Timestamp expired;

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PERSON_ID", nullable = false, updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LOGIN", length = 80)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "NICK", length = 15)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Column(name = "PASSWORD", length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "AVATAR", length = 50)
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "CREATED")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Column(name = "EXPIRED")
    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    private Set<Channel> channelSet = new HashSet<>();//CHANNEL_TO_PERSON

    @ManyToMany
    @JoinTable(name = "channel_to_person",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "CHANNEL_ID"))
    public Set<Channel> getChannelSet() {
        return channelSet;
    }

    public void setChannelSet(Set<Channel> channelSet) {
        this.channelSet = channelSet;
    }
}
