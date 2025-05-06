package hu.alkfejl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/potato")
public class Potato extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Potato");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html");
    }
}
