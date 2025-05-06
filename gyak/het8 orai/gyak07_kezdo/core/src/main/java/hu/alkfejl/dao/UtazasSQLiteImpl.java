package hu.alkfejl.dao;

import hu.alkfejl.model.Utazas;
import hu.alkfejl.utils.ConfigManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtazasSQLiteImpl implements UtazasDAO {
    enum Query {
        INSERT("INSERT INTO Utazas (nev, leiras, napok, emberek, felpanzio, uticel) VALUES (?,?,?,?,?,?)"),
        FIND("SELECT * FROM Utazas"),
        FILTER("SELECT * FROM Utazas WHERE uticel = ?"),
        FILTER_MULTI("SELECT * FROM Utazas WHERE 1 = 1");
        private final String queryString;
        Query(String q) { queryString = q; }
        @Override
        public String toString() {
            return queryString;
        }
    }
    private final String dbURL;

    public UtazasSQLiteImpl(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            dbURL = dbPath;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean add(Utazas utazas) {
        boolean result = false;
        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement ps = conn.prepareStatement(Query.INSERT.queryString)
        ) {
            int index = 1;
            ps.setString(index++, utazas.getNev());
            ps.setString(index++, utazas.getLeiras());
            ps.setInt(index++, utazas.getEjszaka());
            ps.setInt(index++, utazas.getUtasok());
            ps.setBoolean(index++, utazas.getFelpanzio());
            ps.setString(index++, utazas.getUticel());

            int rows = ps.executeUpdate();
            if ( rows == 1 ) {
                result = true;
            } else {
                throw new RuntimeException( "Tobb mint egy sort erintett a beszuras" );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Utazas> find(Utazas utazas) {
        var results = new ArrayList<Utazas>();
        StringBuilder multiQuery = new StringBuilder(Query.FILTER_MULTI.queryString);
        if ( utazas.getNev() != null ) {
            multiQuery.append(" AND nev = ?");
        }
        if ( utazas.getUticel() != null ) {
            multiQuery.append(" AND uticel = ?");
        }
        try (Connection conn = DriverManager.getConnection(dbURL);
            PreparedStatement ps = conn.prepareStatement(multiQuery.toString())
        ) {
            int index = 1;
            if ( utazas.getNev() != null ) {
                ps.setString(index++, utazas.getNev());
            }
            if ( utazas.getUticel() != null ) {
                ps.setString(index++, utazas.getUticel());
            }

            ResultSet rs = ps.executeQuery();
            while( rs.next() ) {
                results.add( retriveUtazasFromRS( rs ) );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    private Utazas retriveUtazasFromRS(ResultSet rs) throws SQLException {
        return new Utazas(
            rs.getString("nev"),
            rs.getString("uticel"),
            rs.getBoolean("felpanzio"),
            rs.getInt("emberek"),
            rs.getInt("napok"),
            rs.getString("leiras")
        );
    }
}
