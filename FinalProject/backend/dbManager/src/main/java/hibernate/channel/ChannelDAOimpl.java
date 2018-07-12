package hibernate.channel;

import database.Channel;

import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ChannelDAOimpl implements ChannelDAO {

    @Override
    public void addChannel(Channel channel) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(channel);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD CHANNEL!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateChannel(Channel channel) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(channel);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE CHANNEL!");
            e.printStackTrace();
        }

    }

    @Override
    public Channel getChannelById(int id) throws SQLException {
        Channel channel = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            channel = (Channel) session.load(Channel.class, id);
        } catch (Exception e) {
            System.out.println("GET CHANNEL!");
            e.printStackTrace();
        }
        return channel;
    }

    @Override
    public List getAllChannels() throws SQLException {
        List<Channel> channels = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL CHANNELS!"); // because we can!
            e.printStackTrace();
        }
        return channels;
    }

    @Override
    public void deleteChannel(Channel channel) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(channel);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE CHANNEL!");
            e.printStackTrace();
        }
    }
}
