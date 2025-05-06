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

@WebServlet("/api/delete/*")
public class DeleteServlet extends HttpServlet {

    ConfigManager config = new ConfigManager(this.getClass());

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getPathInfo();
        System.out.println(str);
        String nev = str.substring(1);

        Utazas u = new Utazas();
        UtazasController uc = UtazasController.getInstance(
                config.getValue("dao"),
                config.getValue("db.url")
        );
        u.setNev(nev);
        if ( uc.delete(u) ) {
            resp.setStatus(204);
        } else {
            resp.setStatus(500);
        }
    }
}
