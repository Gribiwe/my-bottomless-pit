package hibernate.content;

import database.Content;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/27.
 */
public interface ContentDAO {

    void addContent(Content content) throws SQLException;

    void updateContent(Content content) throws SQLException;

    Content getContentById(int id) throws SQLException;

    List getAllContents() throws SQLException;

    void deleteContent(Content content) throws SQLException;

}
