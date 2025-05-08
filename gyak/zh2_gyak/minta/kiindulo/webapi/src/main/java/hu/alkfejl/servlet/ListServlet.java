package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/list", "/alista"})
public class ListServlet extends HttpServlet {
int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //filter letrehozasa es adattagok beallitasa
        Zene zene = new Zene();
        zene.setCim(req.getParameter("cim"));
        zene.setEloado(req.getParameter("eloado"));
        if (req.getParameter("hossz") != null) {
            zene.setHossz(Integer.parseInt(req.getParameter("hossz")));
        }


        //zenek lekerdezese
        ZeneController zc = ZeneController.getInstance(this.getClass());
        var result = zc.find(zene);

        //json elokeszitese
        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        resp.getWriter().println(json);
        resp.setStatus(200);

        //cookie beallitasa
        resp.addCookie(new Cookie("request-number", Integer.toString(++count)));

    }
}
