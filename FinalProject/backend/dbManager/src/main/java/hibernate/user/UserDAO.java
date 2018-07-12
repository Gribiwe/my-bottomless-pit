package hibernate.user;

import database.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void addUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    User getUserById(int id) throws SQLException;

    List getAllUsers() throws SQLException;

    void deleteUser(User user) throws SQLException;

}