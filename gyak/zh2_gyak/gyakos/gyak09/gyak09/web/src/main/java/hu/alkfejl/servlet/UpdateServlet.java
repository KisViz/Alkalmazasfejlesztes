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

@WebServlet("api/update/")
public class UpdateServlet extends HttpServlet {
    private final ConfigManager cfg = new ConfigManager(this.getClass());

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas filter = new Gson().fromJson(req.getReader(), Utazas.class); //osszeszedjuk az adatot a keresbol

        //elkerjuk a controllert
        UtazasController uc = UtazasController.getInstance(cfg.getValue("dao"), cfg.getValue("db.url"));

        if (uc.update(filter)) { //ha sikeres a hozzaadas
            resp.setStatus(200);
        } else {
            resp.setStatus(500);
        }
    }
}
