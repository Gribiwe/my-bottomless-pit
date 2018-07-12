package servlet;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/registration")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jsonBody = (JSONObject) JSONValue.parse(req.getReader().readLine());

        String nickName = jsonBody.get("nickname").toString();
        String email = jsonBody.get("email").toString();
        String password = jsonBody.get("password").toString();

        System.out.println(nickName+": "+email+": "+password);

        resp.setStatus(200);
    }
}
