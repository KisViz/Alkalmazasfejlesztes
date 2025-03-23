package org.example.dao;

import org.example.model.Mobil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilDAOImpl implements MobilDAO{
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    @Override
    public boolean add(Mobil mobil) {
        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement("INSERT into Mobil values (?,?,?)");
                ) {
            int index = 1;
            ps.setString(index++, mobil.getNev());
            ps.setString(index++, mobil.getGyarto());
            ps.setBoolean(index++, mobil.getEsim());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Baj van a beszurasnal");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public List<Mobil> find(Mobil filter) {
        var mobilok = new ArrayList<Mobil>();

        StringBuilder lekerdezes = new StringBuilder("Select * from Mobil where 1=1");
        if (filter.getNev() != null) {
            lekerdezes.append(" and nev = ?");
        }
        if (filter.getGyarto() != null) {
            lekerdezes.append(" and gyarto = ?");
        }
        if (filter.getEsim() != null) {
            lekerdezes.append(" and esim = ?");
        }


        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(lekerdezes.toString());

        ) {
            int index = 1;
            if (filter.getNev() != null) {
                ps.setString(index++, filter.getNev());
            }
            if (filter.getGyarto() != null) {
                ps.setString(index++, filter.getGyarto());
            }
            if (filter.getEsim() != null) {
                ps.setBoolean(index++, filter.getEsim());
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mobil tmp = new Mobil();
                tmp.setNev(rs.getString("nev"));
                tmp.setGyarto(rs.getString("gyarto"));
                tmp.setEsim(rs.getBoolean("esim"));
                mobilok.add(tmp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mobilok;
    }
}
