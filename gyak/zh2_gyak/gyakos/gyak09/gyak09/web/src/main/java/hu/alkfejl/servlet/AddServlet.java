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
    private final ConfigManager cfg = new ConfigManager(this.getClass());
    //ez most azert kell, mert az UtazasController keri a dao-t es a db.url-t, de
    //meg kell nezni, hogy a UC publikus getIsntance mit var es az alapjan csinalni

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas filter = new Gson().fromJson(req.getReader(), Utazas.class); //osszeszedjuk az adatot a keresbol

        //elkerjuk a controllert
        UtazasController uc = UtazasController.getInstance(cfg.getValue("dao"), cfg.getValue("db.url"));

        if (uc.add(filter)) { //ha sikeres a hozzaadas
            resp.setStatus(201);
        } else {
            resp.setStatus(500);
        }
    }
}
