import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/test")
public class PractServlet extends HttpServlet {
    ArrayList<Todoshka> todoList;
    DBConverter db;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setContentType("application/json;charset=UTF-8");

        todoList = db.getTODOList();
        StringBuilder sb = new StringBuilder();
        JSONArray arr = new JSONArray();


        for (Todoshka todo : todoList) {
            JSONObject obj = new JSONObject();
            obj.put("id", todo.getId());
            obj.put("name", todo.getName());
            arr.add(obj);
        }
        System.out.println(arr.toJSONString());
        resp.getWriter().print(arr.toJSONString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject obj = (JSONObject) JSONValue.parse(req.getReader().readLine());

        System.out.println("adding " + obj.get("name").toString());
        db.addTODO(obj.get("name").toString());

        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(200);

        todoList = db.getTODOList();
        JSONArray arr = new JSONArray();
        for (Todoshka todo : todoList) {
            JSONObject obj2 = new JSONObject();
            obj2.put("id", todo.getId());
            obj2.put("name", todo.getName());
            arr.add(obj2);
        }
        System.out.println(arr.toJSONString());
        resp.getWriter().print(arr.toJSONString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject obj = (JSONObject) JSONValue.parse(req.getReader().readLine());


        System.out.println("delete " + obj.get("id").toString());
        db.deleteTODO(obj.get("id").toString());

    }

    @Override
    public void init() throws ServletException {
        db = new DBConverter();
    }
}
