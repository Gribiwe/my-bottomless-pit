package hibernate.message;

import database.Message;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/27.
 */
public interface MessageDAO {

    void addMessage(Message message) throws SQLException;

    void updateMessage(Message message) throws SQLException;

    Message getMessageById(int id) throws SQLException;

    List getAllMessages() throws SQLException;

    void deleteMessage(Message message) throws SQLException;

}
