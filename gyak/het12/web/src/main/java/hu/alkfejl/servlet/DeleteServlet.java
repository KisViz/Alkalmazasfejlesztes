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
    private final ConfigManager cf = new ConfigManager(this.getClass());

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); //elkerjuk urlt, de az utolso / meg benne van
        String nev = pathInfo.substring(1); //eldobjuk a /-t

        UtazasController uc = UtazasController.getInstance(cf.getValue("dao"), cf.getValue("db,url"));
        Utazas utazas = new Utazas();
        utazas.setNev(nev); //ballitjuk a nevet

        if (uc.delete(utazas)) { //elvegezzuk a muveletet esha sikeres
            resp.setStatus(204);
        } else {
            resp.setStatus(500);
        }
    }
}
