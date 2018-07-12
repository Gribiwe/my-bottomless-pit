package hibernate.person;


import database.Person;
import hibernate.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class PersonDAOimpl implements PersonDAO {

    @Override
    public void addPerson(Person person) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("ADD PERSON!");
            e.printStackTrace();
        }

    }

    @Override
    public void updatePerson(Person person) throws SQLException {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("UPDATE PERSON!");
            e.printStackTrace();
        }

    }

    @Override
    public Person getPersonById(int id) throws SQLException {
        Person person = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            person = (Person) session.load(Person.class, id);
        } catch (Exception e) {
            System.out.println("GET PERSON!");
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List getAllPersons() throws SQLException {
        List<Person> people = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SMTH WILL BE HERE
        } catch (Exception e) {
            System.out.println("GET ALL PERSONS!");
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public void deletePerson(Person person) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("DELETE PERSON!");
            e.printStackTrace();
        }
    }
}