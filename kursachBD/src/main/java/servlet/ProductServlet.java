package servlet;

import object.ProductObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to load products");
        JSONArray arr = new JSONArray();
        JSONObject obj;
        for (ProductObject product : ProductService.getInstance().getProducts()) {
            obj = new JSONObject();
            obj.put("id", product.getId());
            obj.put("name", product.getName());
            obj.put("category", product.getCategory());
            obj.put("producer", product.getProducer());
            obj.put("opt", product.getOpt());
            obj.put("roz", product.getRoz());
            obj.put("amountOpt", product.getAmountOpt());
            obj.put("amount", product.getAmount());
            arr.add(obj);
        }

        resp.setStatus(200);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(arr.toJSONString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to create new product");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProductService.getInstance().createProduct(
                jsonBody.get("name").toString(),
                jsonBody.get("category").toString(),
                jsonBody.get("producer").toString(),
                Integer.parseInt(jsonBody.get("opt").toString()),
                Integer.parseInt(jsonBody.get("roz").toString()),
                Integer.parseInt(jsonBody.get("amountToOpt").toString()));

        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to delete product");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProductService.getInstance().removeProduct(Integer.parseInt(jsonBody.get("id").toString()));

        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to rename category");
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        ProductService.getInstance().updateproduct(
                new ProductObject(
                        Integer.parseInt(jsonBody.get("id").toString()),
                        jsonBody.get("name").toString(),
                        Integer.parseInt(jsonBody.get("categoryID").toString()),
                        Integer.parseInt(jsonBody.get("producerID").toString()),
                        Integer.parseInt(jsonBody.get("opt").toString()),
                        Integer.parseInt(jsonBody.get("roz").toString()),
                        Integer.parseInt(jsonBody.get("amountToOpt").toString())));

        resp.setStatus(200);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Request to load a special products");
        JSONArray arr = new JSONArray();
        JSONObject obj;
        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());
        for (ProductObject product : ProductService.getInstance().getProducts(
                Integer.parseInt(jsonBody.get("filterCategory").toString()),
                Integer.parseInt(jsonBody.get("filterProducer").toString()),
                Integer.parseInt(jsonBody.get("filterMinPrice").toString()),
                Integer.parseInt(jsonBody.get("filterMaxPrice").toString()),
                Boolean.parseBoolean(jsonBody.get("filterIsOptPrice").toString()),
                jsonBody.get("filterSortBy").toString()
        )) {
            obj = new JSONObject();
            obj.put("id", product.getId());
            obj.put("name", product.getName());
            obj.put("category", product.getCategory());
            obj.put("producer", product.getProducer());
            obj.put("opt", product.getOpt());
            obj.put("roz", product.getRoz());
            obj.put("amountOpt", product.getAmountOpt());
            obj.put("amount", product.getAmount());
            arr.add(obj);
        }

        resp.setStatus(200);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write(arr.toJSONString());
    }
}
