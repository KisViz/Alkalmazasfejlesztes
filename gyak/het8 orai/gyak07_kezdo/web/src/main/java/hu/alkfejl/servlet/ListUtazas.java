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
import java.util.List;

@WebServlet(urlPatterns = {"/list", "/api/find"})
public class ListUtazas extends HttpServlet {
    private  final  UtazasController uc = UtazasController.getInstance("hu.alkfejl.dao.UtazasSQLiteImpl", "jdbc:sqlite:/home.local/valaki/Letöltések/gyak07_kezdo/utazas.sqlite");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //supert mindig torloni kell

        var filter = new Utazas();

        filter.setNev(req.getParameter("nev"));
        filter.setUticel(req.getParameter("uticel"));

        List<Utazas> lista = uc.find(filter);
//        var u = new Utazas();
//        u.setNev("asdasaas");
//        lista.add(u);



        Gson gson = new Gson();

        resp.getWriter().println(gson.toJson(lista));
        resp.setStatus(200);
        resp.setContentType("application/json");
    }
}
