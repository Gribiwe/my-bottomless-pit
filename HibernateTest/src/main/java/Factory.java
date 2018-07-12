/**
 * Created by Nikita on 2018/04/25.
 */
public class Factory {

    private static StudentDAO studentDAO = null;
    private static Factory instance = null;

    public static Factory getInstance() {
        if (instance==null) {
            instance = new Factory();
        }
        return instance;
    }
    public StudentDAO getStudentDAO() {
        if (studentDAO==null) {
            studentDAO = new StudentDAOImlp();
        }
        return studentDAO;
    }
}
