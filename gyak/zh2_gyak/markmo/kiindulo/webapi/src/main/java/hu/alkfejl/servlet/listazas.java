package hu.alkfejl.servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.alkfejl.model.Zene;
import hu.alkfejl.controller.ZeneController;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/list", "/alista"})
public class listazas extends HttpServlet {
int a = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ++a;
        response.addCookie( new Cookie("request-number", Integer.toString(a)));

        // Szűrő objektum létrehozása
        Zene filter = new Zene()
                .setCim(request.getParameter("cim"))
                .setEloado(request.getParameter("eloado"))
                .setHossz(parseHosszParam(request.getParameter("hossz")));

        // Zenék lekérdezése
        List<Zene> zenek = ZeneController.getInstance(this.getClass()).find(filter);

        // Cookie frissítése először! => EZ NEM KELL, A LEGELEJEN EGY SORBOL HOZHATO
       // updateRequestNumberCookie(request, response);

        // JSON válasz előkészítése
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), zenek);
    }

    private int parseHosszParam(String hosszStr) {
        try {
            return Integer.parseInt(hosszStr);
        } catch (NumberFormatException e) {
            return -1; // Alapértelmezett érték
        }
    }

    private void updateRequestNumberCookie(HttpServletRequest request, HttpServletResponse response) {
        int newCount = 1;
        Cookie existingCookie = null;

        // 1. Meglévő cookie keresése
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("request-number".equalsIgnoreCase(cookie.getName())) {
                    existingCookie = cookie;
                    break;
                }
            }
        }

        // 2. Érték frissítése
        if (existingCookie != null) {
            try {
                newCount = Integer.parseInt(existingCookie.getValue()) + 1;
            } catch (NumberFormatException e) {
                newCount = 1; // Hibás érték esetén reset
            }
        }

        // 3. Új cookie létrehozása
        Cookie newCookie = new Cookie("request-number", String.valueOf(newCount));
        newCookie.setPath("/");
        newCookie.setHttpOnly(true);
        newCookie.setMaxAge(24 * 60 * 60); // 1 nap
        response.addCookie(newCookie);
    }
}