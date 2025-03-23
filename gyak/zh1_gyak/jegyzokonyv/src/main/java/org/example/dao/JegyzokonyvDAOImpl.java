package org.example.dao;

import org.example.model.Jegyzokonyv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JegyzokonyvDAOImpl implements JegyzokonyvDAO{
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    @Override
    public boolean add(Jegyzokonyv jegyzokonyv) {
        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement("insert into Jegyzokonyv (cim,leiras,datum,jegyzo) values (?,?,?,?)");
                ) {
            int index = 1;
            ps.setString(index++, jegyzokonyv.getCim());
            ps.setString(index++, jegyzokonyv.getLeiras());
            ps.setString(index++, jegyzokonyv.getDatum());
            ps.setString(index++, jegyzokonyv.getJegyzo());

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
    public List<Jegyzokonyv> find(Jegyzokonyv filter) {
        var jegyzokonyvek = new ArrayList<Jegyzokonyv>();

        StringBuilder lekerdezes = new StringBuilder("Select * from Jegyzokonyv where 1 = 1");
        if (filter.getCim() != null) {
            lekerdezes.append(" and cim = ?");
        }
        if (filter.getLeiras() != null) {
            lekerdezes.append(" and leiras = ?");
        }
        if (filter.getDatum() != null) {
            lekerdezes.append(" and datum = ?");
        }
        if (filter.getJegyzo() != null) {
            lekerdezes.append(" and jegyzo = ?");
        }

        try (
                Connection conn = DriverManager.getConnection(URL);
                PreparedStatement ps = conn.prepareStatement(lekerdezes.toString());
                ) {
            int index = 1;
            if (filter.getCim() != null) {
                ps.setString(index++,filter.getCim());
            }
            if (filter.getLeiras() != null) {
                ps.setString(index++,filter.getLeiras());
            }
            if (filter.getDatum() != null) {
                ps.setString(index++,filter.getDatum());
            }
            if (filter.getJegyzo() != null) {
                ps.setString(index++,filter.getJegyzo());
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jegyzokonyv tmp = new Jegyzokonyv();

                tmp.setCim(rs.getString("cim"));
                tmp.setLeiras(rs.getString("leiras"));
                tmp.setDatum(rs.getString("datum"));
                tmp.setJegyzo(rs.getString("jegyzo"));

                jegyzokonyvek.add(tmp);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jegyzokonyvek;
    }
}
