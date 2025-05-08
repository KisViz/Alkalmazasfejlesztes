package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.model.Utazas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/preferences")
public class PrefServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utazas utazas = new Gson().fromJson(req.getReader(),Utazas.class); //osszeszedjuk az adatot a keresbol

        Cookie suti = new Cookie("felpanzio", utazas.getFelpanzio().toString());
        //"felpanzio" ~ ez lesz az az attributom nev, ami alapjan azonositani tudjuk
        //masodik meg beleteszi a Truet vagy a Falset
        suti.setMaxAge(300000); //max meddig maradjon
        suti.setPath("/"); //egesz weboldalon ervenyes lesz

        resp.addCookie(suti); //hozzaadjuk a valaszhoz
    }
}
