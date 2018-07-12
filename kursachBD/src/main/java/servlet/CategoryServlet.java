package servlet;

import object.CategoryObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to load categories");
        JSONArray arr = new JSONArray();
        JSONObject obj;
        for(CategoryObject category: CategoryService.getInstance().getCategories()) {
            obj = new JSONObject();
            obj.put("name", category.getName());
            obj.put("id", category.getId());
            arr.add(obj);
        }

        resp.setStatus(200);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(arr.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Request to create new category");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        CategoryService.getInstance().createCategory(jsonBody.get("name").toString());

        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Request to delete category");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        CategoryService.getInstance().removeCategory(Integer.parseInt(jsonBody.get("id").toString()));

        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to rename category");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        CategoryService.getInstance().renameCategory(
                jsonBody.get("name").toString(),
                Integer.parseInt(jsonBody.get("id").toString()));

        resp.setStatus(200);
    }
}
