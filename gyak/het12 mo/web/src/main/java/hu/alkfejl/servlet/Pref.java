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

@WebServlet( urlPatterns = "/api/preference" )
public class Pref extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Utazas u = gson.fromJson( req.getReader(), Utazas.class );
        // NEM KELL/ROSSZ: if ( u.getFelpanzio() )
        // ha csak az igazat mentenénk el, akkor nem írjuk felül a cliens oldali sütit -> beragad a szűrés
        resp.addCookie( new Cookie( CookieNames.FELPANZIO.name, u.getFelpanzio().toString() ) );
    }
}
