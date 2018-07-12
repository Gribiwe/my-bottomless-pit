package hibernate.message;

import database.Message;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class MessageDAOimpl implements MessageDAO {

    @Override
    public void addMessage(Message message) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD MESSAGE!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateMessage(Message message) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(message);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE MESSAGE!");
            e.printStackTrace();
        }

    }

    @Override
    public Message getMessageById(int id) throws SQLException {
        Message message = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            message = (Message) session.load(Message.class, id);
        } catch (Exception e) {
            System.out.println("GET MESSAGE!");
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public List getAllMessages() throws SQLException {
        List<Message> messages = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL MESSAGES!");
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public void deleteMessage(Message message) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(message);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE MESSAGE!");
            e.printStackTrace();
        }
    }
}
