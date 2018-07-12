package servlet;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sclad")
public class ScladServlet extends HttpServlet{

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to add new products");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        System.out.println("adding a "+jsonBody.get("amount").toString()+" to "+jsonBody.get("id").toString());
        ProductService.getInstance().addAmountOfProduct(
                Integer.parseInt(jsonBody.get("id").toString()),
                Integer.parseInt(jsonBody.get("amount").toString()));
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to remove some products");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        System.out.println("removing a "+jsonBody.get("amount").toString()+" to "+jsonBody.get("id").toString());
        ProductService.getInstance().removeAmountOfProduct(
                Integer.parseInt(jsonBody.get("id").toString()),
                Integer.parseInt(jsonBody.get("amount").toString()));
        resp.setStatus(200);
    }
}
