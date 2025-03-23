package hu.alkfejl.dao;

import hu.alkfejl.model.Utazas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtazasSQLite implements UtazasDAO{
    private static final String URL = "jdbc:sqlite:utazas.sqlite";

    @Override
    public boolean add(Utazas utazas) {
        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Utazas (nev, uticel, felpanzio, ejszakak, letszam, leiras) VALUES (?,?,?,?,?,?)");
        ) {
            int index = 1;
            ps.setString(index++, utazas.getNev());
            ps.setString(index++, utazas.getUticel());
            ps.setBoolean(index++, utazas.getFelpanzio());
            ps.setInt(index++, utazas.getEjszaka());
            ps.setInt(index++, utazas.getUtasok());
            ps.setString(index++, utazas.getLeiras());

            int rows = ps.executeUpdate();

            if (rows != 1) {
                throw new RuntimeException("SQL beszúrási hiba");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public List<Utazas> find(Utazas filter) {
        var list = new ArrayList<Utazas>();

        String selecrQuery = "Select * from Utazas where 1=1";
        if (filter.getNev() != null) {
            selecrQuery += " and nev = ?";
        }
        if (filter.getUticel() != null) {
            selecrQuery += " and uticel = ?";
        }


        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(selecrQuery);
        ) {
            int index = 1;
            if (filter.getNev() != null) {
                ps.setString(index++, filter.getNev());
            }
            if (filter.getUticel() != null) {
                ps.setString(index++, filter.getUticel());
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var tmp = new Utazas();
                tmp.setNev(rs.getString("nev"));
                tmp.setUticel(rs.getString("uticel"));
                tmp.setFelpanzio(rs.getBoolean("felpanzio"));
                tmp.setEjszaka(rs.getInt("ejszakak"));
                tmp.setUtasok(rs.getInt("letszam"));
                tmp.setLeiras(rs.getString("leiras"));

                list.add(tmp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }
}
