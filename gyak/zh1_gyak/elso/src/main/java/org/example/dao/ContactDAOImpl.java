package org.example.dao;

import org.example.model.Telefon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements TelefonDAO {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    //beszurjuk az ertekekeet
    @Override
    public boolean add(Telefon telefon) {
        try (
                //elokeszitjuk
                Connection connection = DriverManager.getConnection(URL);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Telefon (nev, gyarto, esim) VALUES (?,?,?)")
                ) {
            int index = 1;
            preparedStatement.setString(index++, telefon.getNev());
            preparedStatement.setString(index++, telefon.getGyarto());
            preparedStatement.setBoolean(index++, telefon.isEsim());

            //elvegezzuk es ellenorzunk
            int rows = preparedStatement.executeUpdate();

            if (rows != 1) {
                throw new RuntimeException("SQL beszúrási hiba");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    //kikeressuk az ertekeket
    @Override
    public List<Telefon> find(Telefon filter) {
        var list = new ArrayList<Telefon>();

        //elokeszitjuk a lekerdezest
        StringBuilder lekerdezes = new StringBuilder("Select * from Telefon where 1=1");
        if (filter.getNev() != null) {
            lekerdezes.append(" and nev = ?");
        }
        if (filter.getGyarto() != null) {
            lekerdezes.append(" and gyarto = ?");
        }
        if (filter.isEsim() != null) { // Feltételezve, hogy `Boolean` lehet és nem primitív `boolean`
            lekerdezes.append(" AND esim = ?");
        }
        //elvegezzuk a lekerdezest
        try(
                Connection connection = DriverManager.getConnection(URL);
                PreparedStatement preparedStatement = connection.prepareStatement(lekerdezes.toString());
                ) {
            int index = 1;
            if (filter.getNev() != null) {
                preparedStatement.setString(index++, filter.getNev());
            }
            if (filter.getGyarto() != null) {
                preparedStatement.setString(index++, filter.getGyarto());
            }
            if (filter.isEsim() != null) {
                preparedStatement.setBoolean(index++, filter.isEsim());
            }

            //lekerjuk a sorokat
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var tmp = new Telefon();
                tmp.setNev(resultSet.getString("nev"));
                tmp.setGyarto(resultSet.getString("gyarto"));
                tmp.setEsim(resultSet.getBoolean("esim"));

                list.add(tmp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
