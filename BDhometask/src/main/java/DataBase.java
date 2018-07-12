import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by nikit on 2018/02/05.
 */
public class DataBase {

    private static final String USER_NAME = "root";
    private static String password;
    private static String bdName;
    public static String tableName;
    private static int cCount;
    private static String DB_URL;;

    public static void initFileToDataBase(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Введите название базы данных");
        bdName = new Scanner(System.in).next();
        DB_URL  = "jdbc:mysql://localhost:3306/"+bdName+"?autoReconnect=true&useSSL=false";

        System.out.println("Введите пароль к "+USER_NAME);
        password = new Scanner(System.in).next();

        try (Connection dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, password);) {
            System.out.println("Подключение удалось");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void transToDB(ArrayList<String> lines){
        try (Connection c = DriverManager.getConnection(DB_URL, USER_NAME, password)){
            Statement statement = c.createStatement();
            for (String line: lines) {
                statement.execute(line);
            }
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void initDataBaseToFile(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Введите название базы данных");
        bdName = new Scanner(System.in).next();
        DB_URL  = "jdbc:mysql://localhost:3306/"+bdName+"?autoReconnect=true&useSSL=false";

        System.out.println("Какую таблицу скопировать?");
        tableName = new Scanner(System.in).next();
        System.out.println("Введите пароль к "+USER_NAME);
        password = new Scanner(System.in).next();

        try (Connection dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, password);
             PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM "+tableName)) {


            System.out.println("Подключение удалось");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> getSQLToFile() {

        ArrayList<String> rows = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, password);
             PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM "+tableName)){
            String meta="";

            ResultSet resultSet = statement.executeQuery();

            cCount = resultSet.getMetaData().getColumnCount();
            for(int i = 1; i<= cCount; i++) {
                meta+=resultSet.getMetaData().getColumnName(i)+", ";
            }
            rows.add(meta.substring(0, meta.lastIndexOf(',')));


            String line;
            while (resultSet.next()) {
                line=resultSet.getInt(1)+", ";
                for(int i = 2; i<= cCount; i++) {
                    line+=resultSet.getString(i);
                    if(i!=cCount) line+=", ";
                }
                rows.add(line);
            }

            for(int i=0; i< rows.size(); i++) {
                System.out.println(rows.get(i));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
