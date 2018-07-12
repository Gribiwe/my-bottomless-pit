package hibernate.user;


import database.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserDAOimpl implements UserDAO {

    @Override
    public void addUser(User user) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD USER!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE USER!");
            e.printStackTrace();
        }

    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = (User) session.load(User.class, id);
        } catch (Exception e) {
            System.out.println("GET USER!");
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List getAllUsers() throws SQLException {
        List<User> users = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL USERS!");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE USER!");
            e.printStackTrace();
        }
    }
}