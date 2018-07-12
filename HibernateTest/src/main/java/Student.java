import javax.persistence.*;

@Entity
@Table(name="student")
public class Student {

    private long id;
    private String name;
    private long age;

    public Student () {

    }

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    public long getId(){
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "age")
    public long getAge() {
        return age;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
