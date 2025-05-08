package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/list", "/alista"})
public class ListServlet extends HttpServlet{

    private static int count = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Zene zene = new Zene();
        zene.setCim(req.getParameter("cim"));
        zene.setEloado(req.getParameter("eloado"));

        String hosszParam = req.getParameter("hossz");
        if (hosszParam != null) {
            int hossz = Integer.parseInt(hosszParam);
            zene.setHossz(hossz);
        }

        ZeneController zc = ZeneController.getInstance(this.getClass());

        var result = zc.find(zene);
        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        resp.getWriter().println(json);
        resp.setStatus(200);

        Cookie cookie = new Cookie("request-number",String.valueOf(count++));
        resp.addCookie(cookie);

    }
}