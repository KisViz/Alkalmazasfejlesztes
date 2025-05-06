package hu.alkfejl.servlet;

import com.google.gson.Gson;
import hu.alkfejl.controller.UtazasController;
import hu.alkfejl.model.Utazas;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Utazas utazas = new Gson().fromJson(req.getReader(), Utazas.class);

        UtazasController uc = UtazasController.getInstance("sqlite", "jdbc:sqlite:/home/sagodiz/Asztal/tmp/alkfejl/gyak06_megoldas/utazas.sqlite");

        if ( uc.add(utazas) ) {
            resp.setStatus(201);
        } else {
            resp.setStatus(500);
        }
    }
}
