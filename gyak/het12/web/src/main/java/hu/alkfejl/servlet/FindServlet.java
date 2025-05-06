package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/api/find", "/api/list"})
public class FindServlet extends HttpServlet {
    private final ConfigManager cfg = new ConfigManager(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas utazas = new Utazas(); //ez lesz a filter

        utazas.setUticel(req.getParameter("uticel")); //lekerjuk az adatokat
        utazas.setNev(req.getParameter("nev"));

        //suti kezeleshez
        Cookie[] sutik = req.getCookies();
        if (null != sutik) { //ha van sutink
            for (var c : sutik) { //megkesessuk a felpanzio sutit
                if ("felpanzio".equals(c.getName()) && Boolean.parseBoolean(c.getValue())) {
                    utazas.setFelpanzio(true);
                }
            }
        }

        //elkerjuk a controllert
        UtazasController uc = UtazasController.getInstance(cfg.getValue("dao"), cfg.getValue("db.url"));

        //kikeressuk (ez listat ad vissza -> jsonne kell alakitani)
        var result = uc.find(utazas);

        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); //beallitjuk a kodolast
        resp.setContentType("application/json");
        resp.getWriter().println(json); //valaszba irjuk a dolgot
        resp.setStatus(200); //mert ezt varja a dao
    }
}
