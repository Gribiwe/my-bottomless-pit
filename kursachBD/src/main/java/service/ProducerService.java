package service;

import object.ProducerObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerService {

    private static ProducerService instance;

    public static ProducerService getInstance() {
        if (instance == null) {
            instance = new ProducerService();
        }
        return instance;
    }

    public void renameProducer(String name, int id) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE producer SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProducer(int id) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM producer WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProducerObject> getProducers() {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT  * FROM producer");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ProducerObject> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new ProducerObject(
                        resultSet.getString("name"),
                        resultSet.getInt("id")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void createProducer(String name) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO producer (name) values (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            System.out.println("Created producer "+name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
