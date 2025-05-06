package hu.alkfejl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/potato")
public class PotatoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/html");
        var writer = resp.getWriter();

        String queryParameter = req.getParameter("query");

        if ( queryParameter == null ) {
            resp.setStatus(400);
            writer.println("Noooo, noooooo! by Consuela");
            return;
        }

        writer.println(
                "<title>" + queryParameter + "</title>"
        );
        writer.println("<body><h1>H1 cím</h1></body>");
        resp.setStatus(200);
    }
}
