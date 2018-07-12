import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.json.simple.JSONArray;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void renameTable(int id, String name) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("UPDATE tablesUP SET name = ? WHERE id = ?")) {
            st.setString(1, name);
            st.setInt(2, id);
            st.execute();
            System.out.println("renamed table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeTable(int id) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("DELETE FROM tablesUP WHERE id = ?")) {
            st.setInt(1, id);
            st.execute();
            System.out.println("table removed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createTable(){
        JSONArray defaultBody = new JSONArray();
        JSONArray bufArray = new JSONArray();
        bufArray.add(" ");
        bufArray.add(" ");
        defaultBody.add(bufArray);
        defaultBody.add(bufArray);
        System.out.println("Created new table: "+defaultBody.toJSONString());
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("INSERT INTO tablesUp (name, body) VALUES ('NewTable', ?)")) {

            st.setString(1, defaultBody.toJSONString());
            st.execute();
            return defaultBody.toJSONString();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveTable(int id, String data) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("UPDATE tablesUP SET body = ? WHERE id = ?")) {
            st.setString(1, data);
            st.setInt(2, id);
            st.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getFirstTableID() {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("SELECT * FROM tablesUp")) {

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<TableMeta> loadTables() {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("SELECT * FROM tablesUp")) {

            ResultSet rs = st.executeQuery();
            List<TableMeta> tables = new ArrayList<>();
            while (rs.next()){
                tables.add(new TableMeta(rs.getInt("id"), rs.getString("name")));
            }
            return tables;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getTableBody(int id) {
        try(PreparedStatement st = dataSource.getConnection().
                prepareStatement("SELECT * FROM tablesUP where id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();


            if (rs.next()){
                return rs.getString("body");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
