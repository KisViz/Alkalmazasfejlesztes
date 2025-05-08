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
    private int count = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Zene filter = new Zene(); //letrehozzuk a filtert es beallitjuk az adattagokat
        filter.setCim(req.getParameter("cim"));
        filter.setEloado(req.getParameter("eloado"));
        if (req.getParameter("hossz") != null) {
            filter.setHossz(Integer.parseInt(req.getParameter("hossz")));
            //ez a check ide elvileg azert kell, mert ha null, akkor nullt allitana ba es
            //a modelben pedig -1 az alapertelmezett ertek es megborul
        }

        //zenek lekerdezese
        ZeneController zc = ZeneController.getInstance(this.getClass());
        var zenek = zc.find(filter);

        //vissza adjuk az eredmenyt
        resp.getWriter().println(new Gson().toJson(zenek));
        resp.setStatus(200);

        //suti kezelese
        resp.addCookie(new Cookie("request-number", Integer.toString(++count))); //cookie beallitasa
    }
}
