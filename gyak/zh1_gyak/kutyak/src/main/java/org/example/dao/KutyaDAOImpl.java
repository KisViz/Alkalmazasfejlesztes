package org.example.dao;

import org.example.model.Kutya;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KutyaDAOImpl implements KutyaDAO{
    private final String URL = "jdbc:sqlite:identifier.sqlite";

    @Override
    public boolean add(Kutya kutya) {
        try(
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Kutya (nev,faj,gazda) values (?,?,?)");
                ) {
            int index = 1;
            ps.setString(index++, kutya.getNev());
            ps.setString(index++, kutya.getFaj());
            ps.setString(index++, kutya.getGazda());

            int rows = ps.executeUpdate();
            if (rows != 1) {
                throw new RuntimeException("Baj van a beszurasnal:(");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public List<Kutya> findAll() {
        List<Kutya> kutyak = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement("SELECT * from Kutya");
                ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kutya tmp = new Kutya();

                tmp.setNev(rs.getString("nev"));
                tmp.setFaj(rs.getString("faj"));
                tmp.setGazda(rs.getString("gazda"));

                kutyak.add(tmp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return kutyak;
    }
}
