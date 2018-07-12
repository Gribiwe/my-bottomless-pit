import java.util.UUID;

public class Student {
    private int age;
    private UUID id;
    private String name;
    private String secondName;

    public Student(int age, String name, String secondName) {
        this.age = age;
        this.id = UUID.randomUUID();
        this.name = name;
        this.secondName = secondName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {

        return age;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }
}
