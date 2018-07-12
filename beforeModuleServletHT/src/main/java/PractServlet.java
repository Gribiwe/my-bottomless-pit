import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class PractServlet extends HttpServlet {
    DBConverter db;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("requested " + req.getParameter("name"));
        SchoolClass schoolClass = db.getSchoolClass(req.getParameter("name"));

        if(schoolClass!=null) {
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(200);
            JSONObject obj = new JSONObject();
            obj.put("name", schoolClass.getName());
            obj.put("amount", schoolClass.getAmount());
            obj.put("teacher", schoolClass.getTeacher());

            System.out.println(obj.toJSONString());
            resp.getWriter().print(obj.toJSONString());
        } else {
            resp.setContentType("text/plain");
            resp.setStatus(200);
            System.out.println("NOTFOUND");
            resp.getWriter().print("NOTFOUND");
        }
    }

    @Override
    public void init() throws ServletException {
        db = new DBConverter();
        FileReader fileReader = new FileReader();
        SchoolClassBuilder builder = new SchoolClassBuilder();

        db.initTable(builder.buildSchoolClass(fileReader.getLines()));
    }
}
