import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsReq = (JSONObject) JSONValue.parse(req.getReader().readLine());
        System.out.println(jsReq.toJSONString());
        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(200);
        switch (jsReq.get("type").toString()) {
            case "saveTable":
                db.saveTable(Integer.parseInt(jsReq.get("id").toString()), jsReq.get("data").toString());
                break;
            case "createTable":
                resp.getWriter().print(db.createTable());
                break;
            case "renameTable":
                db.renameTable(Integer.parseInt(jsReq.get("id").toString()), jsReq.get("name").toString());
                resp.getWriter().print("ok");
                break;
            case "removeTable":
                db.removeTable(Integer.parseInt(jsReq.get("id").toString()));
                resp.getWriter().print("ok");
                break;
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");

        resp.setContentType("application/json;charset=UTF-8");
        resp.setStatus(200);

        System.out.println("type: " + type);
        switch (type) {
            case "needTables":
                JSONArray arr = new JSONArray();
                for (TableMeta table : db.loadTables()) {
                    JSONObject obj = new JSONObject();
                    obj.put("name", table.getName());
                    obj.put("id", table.getId());
                    arr.add(obj);
                }
                arr.add(db.getFirstTableID());
                System.out.println(arr.toJSONString());
                resp.getWriter().print(arr.toJSONString());
                break;
            case "needTable":
                System.out.println("# "+Integer.parseInt(req.getParameter("id")));
                String arr2 = db.getTableBody(Integer.parseInt(req.getParameter("id")));
                System.out.println(arr2);
                resp.getWriter().print(arr2);
                break;
        }
    }

    @Override
    public void init() throws ServletException {
        db = new DBConverter();
    }
}
