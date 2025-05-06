package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// lehetséges több útvonalat is egy servletre kötni.
@WebServlet(urlPatterns = {"/api/find", "/api/list"})
public class FindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var utazas = new Utazas();
        /*
        * A szűrés alapból null-okat használ ezekre az adattagokra, így ha nincs paraméter, akkor null-t kapunk vissza, azaz effektíve nem változtattunk semmin.
        * */
        utazas.setUticel(req.getParameter("uticel"));
        utazas.setNev(req.getParameter("nev"));

        UtazasController uc = UtazasController.getInstance("sqlite", "jdbc:sqlite:/home/sagodiz/Asztal/tmp/alkfejl/gyak06_megoldas/utazas.sqlite");
        var list = uc.find(utazas);

        Gson gson = new Gson();
        String json = gson.toJson(list);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }
}
