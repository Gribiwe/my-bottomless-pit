package third;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nikit on 17.02.2018.
 */
public class Database {
    private String DB_URL = "jdbc:mysql://localhost:3306/module23?autoReconnect=true&useSSL=false";
    private String USER_NAME = "root";
    private String PASSWORD = "0801";

    public Database(String tableName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            createTable(tableName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    }

    //cName, chCount, NSM

    private void createTable(String tableName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+tableName +
                     "(id int(8) AUTO_INCREMENT PRIMARY KEY, " +
                     "cName VARCHAR (4), " +
                     "chCount int(4), " +
                     "NSM VARCHAR (256))")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ClassInfo getClassInfo(String cName, String tableName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE cName = ?")) {
            statement.setString(1, cName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first())
                return new ClassInfo(resultSet.getString("cName"),
                        resultSet.getInt("chCount"),
                        resultSet.getString("NSM"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void flushClasses(ArrayList<ClassInfo> clList, String tableName) {

        for (ClassInfo classInfo : clList) {

            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement
                         ("INSERT INTO "+tableName+" (cName, chCount, NSM) VALUES (?, ?, ?)")) {

                statement.setString(1, classInfo.getName());
                statement.setInt(2, classInfo.getChildrenCount());
                statement.setString(3, classInfo.getFSM());
                statement.execute();

                System.out.println("inserted " + classInfo.getName() + "|" + classInfo.getChildrenCount() + "|" + classInfo.getFSM());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
