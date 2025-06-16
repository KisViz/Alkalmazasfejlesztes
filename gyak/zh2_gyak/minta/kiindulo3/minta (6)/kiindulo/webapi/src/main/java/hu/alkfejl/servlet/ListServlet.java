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

@WebServlet(urlPatterns = {"/api/list", "/alista"})
public class ListServlet extends HttpServlet {
    int szaml = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Zene zene = new Zene();
        zene.setCim(req.getParameter("cim"));
        zene.setEloado(req.getParameter("eloado"));
        if (null != req.getParameter("hossz")) {
            zene.setHossz(Integer.parseInt(req.getParameter("hossz")));
        }

        ZeneController zc = ZeneController.getInstance(this.getClass());
        var zenek = zc.find(zene);

        resp.getWriter().println(new Gson().toJson(zenek));
        resp.setStatus(200);

        resp.addCookie(new Cookie("request-number", Integer.toString(++szaml)));
    }
}
