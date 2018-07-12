package hibernate.content;

import database.Content;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class ContentDAOimpl implements ContentDAO {

    @Override
    public void addContent(Content content) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(content);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD CONTENT!");
            e.printStackTrace();
        }

    }

    @Override
    public void updateContent(Content content) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(content);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE CONTENT!");
            e.printStackTrace();
        }

    }

    @Override
    public Content getContentById(int id) throws SQLException {
        Content content = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            content = (Content) session.load(Content.class, id);
        } catch (Exception e) {
            System.out.println("GET CONTENT!");
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public List getAllContents() throws SQLException {
        List<Content> contents = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL CONTENTS!");
            e.printStackTrace();
        }
        return contents;
    }

    @Override
    public void deleteContent(Content content) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(content);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE CONTENT!");
            e.printStackTrace();
        }
    }
}
