package hu.alkfejl.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import hu.alkfejl.controller.ZeneController;
import hu.alkfejl.model.Zene;
import hu.alkfejl.utils.ConfigManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/add")
public class AddServlet extends HttpServlet {
//    private final ConfigManager cfg = new ConfigManager(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] sutik = req.getCookies();
        if (sutik != null) {
            for (var c : sutik) {
                if (c.getValue() != null) {
                    c.setValue("modded" + c.getValue());
                    resp.addCookie(c);
                }
            }
        }


        try {
            ZeneController zc = ZeneController.getInstance(this.getClass());
            Zene zene = new Gson().fromJson(req.getReader(), Zene.class);

            if (zc.add(zene)) {
                resp.setStatus(201);
            } else {
                resp.setStatus(500);
            }
        } catch (JsonSyntaxException e) {
            resp.setStatus(400);
        }


    }
}
