package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //cookie kezeles
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (var cooie : cookies) {
                cooie.setValue("modded" + cooie.getValue()); //atirjuk
                resp.addCookie(cooie); //visszarakjuk
            }
        }

        try {
            Zene filter = new Gson().fromJson(req.getReader(), Zene.class); //elszedjuk az adatot
            ZeneController zc = ZeneController.getInstance(this.getClass()); //elkerjuk a controllert

            if (zc.add(filter)) { //sikerul
                resp.setStatus(201);
            } else { //egyebb miatt nem sikerul
                resp.setStatus(500);
            }
        } catch (Exception e) { //json miatt nem sikerul
            resp.setStatus(400);
        }
    }
}
