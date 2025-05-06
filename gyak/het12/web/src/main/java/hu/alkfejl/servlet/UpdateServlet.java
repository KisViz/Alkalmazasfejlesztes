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

@WebServlet("/api/update")
public class UpdateServlet extends HttpServlet {
    private final ConfigManager cf = new ConfigManager(this.getClass());

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas utazas = new Gson().fromJson(req.getReader(), Utazas.class); //osszeszedjuk az adattot

        UtazasController uc = UtazasController.getInstance(cf.getValue("dao"), cf.getValue("db.url"));

        if (uc.update(utazas)) { //ha sikeres frissites
            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.setStatus(200); //lehet siman szamokat is adni
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }


    }
}
