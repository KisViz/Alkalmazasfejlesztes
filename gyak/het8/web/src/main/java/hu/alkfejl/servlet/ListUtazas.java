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

    private final UtazasController uc = UtazasController.getInstance("hu.alkfejl.dao.UtazasSQLiteImpl", "jdbc:sqlite:C:/Users/Albert/IdeaProjects/Alkalmazasfejlesztes/gyak/het8/utazas.sqlite");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var filter = new Utazas();

        filter.setNev(req.getParameter("nev"));
        filter.setUticel(req.getParameter("uticel"));

        List<Utazas> list = uc.find(filter);

        Gson gson = new Gson();

        resp.getWriter().println(gson.toJson(list));
        resp.setStatus(200);
        resp.setContentType("application/json");
    }
}
