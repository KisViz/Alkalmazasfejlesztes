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

// lehetséges több útvonalat is egy servletre kötni.
@WebServlet(urlPatterns = {"/api/find", "/api/list"})
public class FindServlet extends HttpServlet {
    private final ConfigManager config = new ConfigManager(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var utazas = new Utazas();
        /*
        * A szűrés alapból null-okat használ ezekre az adattagokra, így ha nincs paraméter, akkor null-t kapunk vissza, azaz effektíve nem változtattunk semmin.
        * */
        utazas.setUticel(req.getParameter("uticel"));
        utazas.setNev(req.getParameter("nev"));


        Cookie[] cookies = req.getCookies();
        if ( cookies != null ) {
            for ( var c : cookies ) {
                if ( c.getName().equals( CookieNames.FELPANZIO.name )
                && Boolean.parseBoolean( c.getValue() ) ) {
                    utazas.setFelpanzio( true );
                }
            }
        }

        UtazasController uc = UtazasController.getInstance(
                config.getValue("dao"),
                config.getValue("db.url")
        );

        var list = uc.find(utazas);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }
}
