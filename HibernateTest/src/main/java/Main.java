import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/25.
 */
public class Main {
    public static void main(String [] args) {

        Student s1 = new Student();
        Student s2 = new Student();

        s1.setName("Саша Первый");
        s1.setAge(20l);
        s2.setName("Кекыч Копчанский");
        s2.setAge(22l);

        try {
            Factory.getInstance().getStudentDAO().addStudent(s1);
            Factory.getInstance().getStudentDAO().addStudent(s2);

            List<Student> studs = Factory.getInstance().getStudentDAO().getAllStudents();

            System.out.println("====");
            for (Student student: studs) {
                System.out.print("id: "+student.getId());
                System.out.print("name: "+student.getName());
                System.out.print("age: "+student.getAge());
                System.out.println();
            }
            System.out.println("====");

        } catch(HibernateException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
