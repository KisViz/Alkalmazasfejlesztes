package hu.alkfejl.servlet;

import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/delete/*") //a * kell, hogy a vegen barmi lehessen
public class DeleteServlet extends HttpServlet {
    private final ConfigManager cfg = new ConfigManager(this.getClass());
    //ez most azert kell, mert az UtazasController keri a dao-t es a db.url-t, de
    //meg kell nezni, hogy a UC publikus getIsntance mit var es az alapjan csinalni


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); //elkerjuk urlt, de az utolso / meg benne van
        String nev = pathInfo.substring(1); //eldobjuk a /-t

        //elkerjuk a controllert
        UtazasController uc = UtazasController.getInstance(cfg.getValue("dao"), cfg.getValue("db.url"));
        Utazas filter = new Utazas();
        filter.setNev(nev); //beallitjuk a nevet

        if (uc.delete(filter)) { //ha sikeres a torles
            resp.setStatus(204);
        } else {
            resp.setStatus(500);
        }
    }
}
