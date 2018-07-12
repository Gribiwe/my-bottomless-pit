import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImlp implements StudentDAO {
    public void addStudent(Student student) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Student getStudentById(long id) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.load(Student.class, id);
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public List getAllStudents() throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createCriteria(Student.class).list();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    public void deleteStudent(Student student) throws SQLException {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}