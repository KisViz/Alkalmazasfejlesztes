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
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (var cookie : cookies) {
                if (cookie != null) {
                    cookie.setValue("modded" + cookie.getValue());
                    resp.addCookie(cookie);
                }
            }
        }

        try {
            Zene filter = new Gson().fromJson(req.getReader(), Zene.class);
            ZeneController zc = ZeneController.getInstance(this.getClass());

            if (zc.add(filter)) {
                resp.setStatus(201);
            } else {
                resp.setStatus(500);
            }
        } catch (Exception e) {
            resp.setStatus(400);
        }
    }
}
