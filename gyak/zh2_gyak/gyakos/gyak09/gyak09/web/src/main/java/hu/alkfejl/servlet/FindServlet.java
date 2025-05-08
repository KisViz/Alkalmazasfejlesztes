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
    //ez most azert kell, mert az UtazasController keri a dao-t es a db.url-t, de
    //meg kell nezni, hogy a UC publikus getIsntance mit var es az alapjan csinalni

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas filter = new Utazas(); //ez lesz a filter a kereseskor

        //beallitjuk az adattagjait
        filter.setUticel(req.getParameter("uticel"));
        filter.setNev(req.getParameter("nev"));
        Cookie[] cookies = req.getCookies();
        for (var cookie : cookies) {
            //itt valamiert csak a felpanziosok kellenek ezert van igy
            if ("felpanzio".equals(cookie.getName()) && Boolean.parseBoolean(cookie.getValue())) {
                filter.setFelpanzio(true);
            }
        }

        //elkerjuk a controllert
        UtazasController uc = UtazasController.getInstance(cfg.getValue("dao"), cfg.getValue("db.url"));

        var result = uc.find(filter); //kikeressuk (ez listat ad vissza -> jsonne kell alakitani)

        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); //beallitjuk a kodolast
        resp.setContentType("application/json");
        resp.getWriter().println(json); //valaszba irjuk a dolgot
        resp.setStatus(200); //mert ezt varja a dao
    }
}
