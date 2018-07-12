package object;

public class ProducerObject {
    private String name;
    private int id;

    public ProducerObject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}