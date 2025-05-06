package hu.alkfejl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet( urlPatterns = "/api/home" )
public class Home extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie( "secret", "secretValue" );
        resp.addCookie( cookie );
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if ( cookies == null ) { // nem üres vector, hanem null lehet
            resp.setStatus( 400 );
            return;
        }
        for ( Cookie c : cookies ) {
            //    Biztosan létezik az objektum        Ez pedig akár null is lehet -> nullptr dereferencia
            if ( "secret".equals( c.getName() ) && c.getValue().equals( "secretValue" ) ) {
                resp.getWriter().println( "Hello!" );
            }
        }
    }
}
