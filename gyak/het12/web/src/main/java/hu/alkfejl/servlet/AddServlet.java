package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/add")
public class AddServlet extends HttpServlet {
    private final ConfigManager cf = new ConfigManager(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas utazas = new Gson().fromJson(req.getReader(), Utazas.class); //osszeszedjuk az adatot a keresbol

        UtazasController uc = UtazasController.getInstance(cf.getValue("dao"), cf.getValue("db.url"));

        if (uc.add(utazas)) { //ha sikerul adatbbe berakni
            resp.setStatus(201);
        } else { //ha fail
            resp.setStatus(500);
        }


    }
}
