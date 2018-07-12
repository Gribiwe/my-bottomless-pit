package object;

public class ProductObject {
    private int id;
    private String name;
    private String category;
    private String producer;
    private int categoryID;
    private int producerID;
    private int opt;
    private int roz;
    private int amountOpt;
    private int amount;





    public ProductObject(int id, String name, int categoryID, int producerID, int opt, int roz, int amountOpt) {
        this.id = id;
        this.name = name;
        this.categoryID = categoryID;
        this.producerID = producerID;
        this.opt = opt;
        this.roz = roz;
        this.amountOpt = amountOpt;
    }

    public ProductObject(int id, String name, String category, String producer, int opt, int roz, int amountOpt, int amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.producer = producer;
        this.opt = opt;
        this.roz = roz;
        this.amountOpt = amountOpt;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getProducerID() {
        return producerID;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }

    public int getOpt() {
        return opt;
    }

    public int getRoz() {
        return roz;
    }

    public int getAmountOpt() {
        return amountOpt;
    }
}
