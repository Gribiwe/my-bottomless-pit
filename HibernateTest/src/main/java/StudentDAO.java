import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nikita on 2018/04/25.
 */
public interface StudentDAO {
    public void addStudent(Student student) throws SQLException;
    public void updateStudent (Student student) throws SQLException;
    public Student getStudentById(long id) throws SQLException;
    public List getAllStudents() throws SQLException;
    public void deleteStudent (Student student) throws SQLException;
}
