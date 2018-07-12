package service;

import object.ProductObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance;

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public void addAmountOfProduct(int id, int amount){

        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE product" +
                                    " SET amount = amount + ?" +
                                    " WHERE id = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeAmountOfProduct(int id, int amount){
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE product" +
                                    " SET amount = amount - ?" +
                                    " WHERE id = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public List<ProductObject>getProducts(int categoryID, int producerID, int priceFrom,
                                          int priceTo, boolean isOpt, String sortBy) {

        String somePrice = isOpt ? "rozPrice" : "optPrice";
        String sort = "";
        String categorySravn = categoryID==0? "<>": "=";
        String producerSravn = producerID==0? "<>": "=";

        switch (sortBy) {
            case "sortByNon": sort="ORDER BY product.id ASC "; break;
            case "sortByHName": sort="ORDER BY product.name ASC"; break;
            case "sortByLName": sort="ORDER BY product.name DESC"; break;
            case "sortByHPrice": sort="ORDER BY product."+somePrice+" DESC"; break;
            case "sortByLPrice": sort="ORDER BY product."+somePrice+" ASC"; break;
        }

        String quer = "select product.id, product.amount, product.name, " +
                "category.name, producer.name," +
                " product.optPrice, product.rozPrice," +
                " product.amountToOpt " +
                "from product " +
                "inner join category " +
                "on product.category = category.id " +
                "inner join producer " +
                "on product.producer = producer.id " +
                "WHERE category.id "+categorySravn+" ? AND "+
                "producer.id "+producerSravn+" ? AND "+
                "product."+somePrice+" >= ? AND " +
                "product."+somePrice+" <= ? " +
                sort+";";

        System.out.println(quer);

        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(quer);

            preparedStatement.setInt(1, categoryID);
            preparedStatement.setInt(2, producerID);
            preparedStatement.setInt(3, priceFrom);
            preparedStatement.setInt(4, priceTo);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductObject> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new ProductObject(
                        resultSet.getInt("product.id"),
                        resultSet.getString("product.name"),
                        resultSet.getString("category.name"),
                        resultSet.getString("producer.name"),
                        resultSet.getInt("product.optPrice"),
                        resultSet.getInt("product.rozPrice"),
                        resultSet.getInt("product.amountToOpt"),
                        resultSet.getInt("product.amount")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<ProductObject> getProducts() {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "select product.id, product.amount, product.name, " +
                                    "category.name, producer.name," +
                                    " product.optPrice, product.rozPrice," +
                                    " product.amountToOpt " +
                                    "from product " +
                                    "inner join category " +
                                        "on product.category = category.id " +
                                    "inner join producer " +
                                        "on product.producer = producer.id;");

            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProductObject> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new ProductObject(
                        resultSet.getInt("product.id"),
                        resultSet.getString("product.name"),
                        resultSet.getString("category.name"),
                        resultSet.getString("producer.name"),
                        resultSet.getInt("product.optPrice"),
                        resultSet.getInt("product.rozPrice"),
                        resultSet.getInt("product.amountToOpt"),
                        resultSet.getInt("product.amount")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void updateproduct(ProductObject product) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE product" +
                                    " SET name = ?, category = ?," +
                                    " producer = ?, optPrice = ?," +
                                    " rozPrice = ?, amountToOpt = ? " +
                                    " WHERE id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getCategoryID());
            preparedStatement.setInt(3, product.getProducerID());
            preparedStatement.setInt(4, product.getOpt());
            preparedStatement.setInt(5, product.getRoz());
            preparedStatement.setInt(6, product.getAmountOpt());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(int id) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createProduct(String name, String category, String producer, int opt, int roz, int amountToOpt) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO product " +
                            "(name, category, producer, optPrice, rozPrice, amountToOpt)" +
                            " values (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setString(3, producer);
            preparedStatement.setInt(4, opt);
            preparedStatement.setInt(5, roz);
            preparedStatement.setInt(6, amountToOpt);
            preparedStatement.execute();
            System.out.println("Created product "+name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
