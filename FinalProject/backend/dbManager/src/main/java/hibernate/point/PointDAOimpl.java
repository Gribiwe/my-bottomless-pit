package hibernate.point;

import database.Point;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PointDAOimpl implements PointDAO {

    @Override
    public void addPoint(Point point) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(point);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD POINT!");
            e.printStackTrace();
        }

    }

    @Override
    public void updatePoint(Point point) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(point);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE POINT!");
            e.printStackTrace();
        }

    }

    @Override
    public Point getPointById(int id) throws SQLException {
        Point point = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            point = (Point) session.load(Point.class, id);
        } catch (Exception e) {
            System.out.println("GET POINT!");
            e.printStackTrace();
        }
        return point;
    }

    @Override
    public List getAllPoints() throws SQLException {
        List<Point> points = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL POINTS!"); // because we can!
            e.printStackTrace();
        }
        return points;
    }

    @Override
    public void deletePoint(Point point) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(point);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE POINT!");
            e.printStackTrace();
        }
    }
}
