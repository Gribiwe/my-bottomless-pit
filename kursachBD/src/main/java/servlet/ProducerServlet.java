package servlet;

import object.ProducerObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.ProducerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/producer")
public class ProducerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to load producers");
        JSONArray arr = new JSONArray();
        JSONObject obj;
        for(ProducerObject producer: ProducerService.getInstance().getProducers()) {
            obj = new JSONObject();
            obj.put("name", producer.getName());
            obj.put("id", producer.getId());
            arr.add(obj);
        }

        resp.setStatus(200);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(arr.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Request to create new producer");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProducerService.getInstance().createProducer(jsonBody.get("name").toString());

        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Request to delete producer");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProducerService.getInstance().removeProducer(Integer.parseInt(jsonBody.get("id").toString()));

        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to rename producer");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProducerService.getInstance().renameProducer(
                jsonBody.get("name").toString(),
                Integer.parseInt(jsonBody.get("id").toString()));

        resp.setStatus(200);
    }
}
