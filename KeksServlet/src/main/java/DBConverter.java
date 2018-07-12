import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by nikit on 10.02.2018.
 */
public class DBConverter {

    private HikariDataSource dataSource;

    HikariConfig hikariConfig;

    public DBConverter() {
        hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        dataSource.setMaximumPoolSize(48);
        dataSource.setMaxLifetime(60000);
        dataSource.setMinimumIdle(13);
        dataSource.setIdleTimeout(30000);
        dataSource.setLeakDetectionThreshold(48);
    }

    public void deleteTODO(String id) {

        try (PreparedStatement st = dataSource.getConnection().
                prepareStatement("DELETE from todo_list WHERE id= ?")) {
            st.setString(1, id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addTODO(String name) {

        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("INSERT INTO todo_list (name) values (?)")) {
            st.setString(1, name);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Todoshka> getTODOList() {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("select * from todo_list")) {

            ResultSet resultSet = st.executeQuery();
            ArrayList<Todoshka> buf = new ArrayList<>();

            while (resultSet.next()) {
                buf.add(new Todoshka(resultSet.getString("id"), resultSet.getString("name")));
            }
            return buf;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
