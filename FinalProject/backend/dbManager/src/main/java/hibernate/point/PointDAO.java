package hibernate.point;

import database.Point;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/27.
 */
public interface PointDAO {

    void addPoint(Point point) throws SQLException;

    void updatePoint(Point point) throws SQLException;

    Point getPointById(int id) throws SQLException;

    List getAllPoints() throws SQLException;

    void deletePoint(Point point) throws SQLException;

}
