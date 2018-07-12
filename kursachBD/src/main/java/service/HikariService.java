package service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariService {

    private static HikariService instance;

    public static HikariService getInstance() {
        if (instance == null) {
            instance = new HikariService();
        }
        return instance;
    }

    private HikariDataSource dataSource;

    protected HikariService() {
        HikariConfig hikariConfig = new HikariConfig("/hikary.properties");
        dataSource = new HikariDataSource(hikariConfig);
        dataSource = new HikariDataSource(hikariConfig);
        dataSource.setMaximumPoolSize(48);
        dataSource.setMaxLifetime(60000);
        dataSource.setMinimumIdle(13);
        dataSource.setIdleTimeout(30000);
        dataSource.setLeakDetectionThreshold(48);
    }

    protected Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
