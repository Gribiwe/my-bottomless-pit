package service;

import object.CategoryObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private static CategoryService instance;

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public void renameCategory(String name, int id) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE category SET name = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCategory(int id) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM category WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoryObject> getCategories() {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT  * FROM category");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CategoryObject> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new CategoryObject(
                        resultSet.getString("name"),
                        resultSet.getInt("id")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void createCategory(String name) {
        try (Connection connection = HikariService.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO category (name) values (?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            System.out.println("Created category "+name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
