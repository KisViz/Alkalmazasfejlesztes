package hu.alkfejl.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/add")
public class hozzaadas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Cookie-k módosítása és visszaállítása
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                Cookie modifiedCookie = new Cookie(cookie.getName(), "modded" + cookie.getValue());
                modifiedCookie.setPath("/");
                response.addCookie(modifiedCookie);
            }
        }

        // JSON feldolgozása
        ObjectMapper mapper = new ObjectMapper();
        try {
            Zene zene = mapper.readValue(request.getInputStream(), Zene.class);
            boolean success = ZeneController.getInstance(this.getClass()).add(zene);

            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Érvénytelen JSON formátum");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}