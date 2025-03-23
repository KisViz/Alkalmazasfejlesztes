package org.example.dao;

import org.example.model.Mobil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MobilSQLite implements MobilDAO {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";
    @Override
    public List<Mobil> findAll() {
        var list = new ArrayList<Mobil>();

        try(
                Connection c = DriverManager.getConnection( URL );
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "SELECT * FROM Mobil" );
                ) {

            while( rs.next() ) {
                var tmp = new Mobil();

                tmp.setNev( rs.getString( "nev" ) );
                tmp.setGyarto( rs.getString("gyarto" ) );
                tmp.setEsim( rs.getBoolean("esim") );

                list.add( tmp );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public boolean save(Mobil toSave) {
        try(
                Connection c = DriverManager.getConnection( URL );
                PreparedStatement ps = c.prepareStatement( "INSERT INTO Mobil VALUES (?,?,?)")
        ) {
            int index = 1;
            ps.setString( index++, toSave.getNev() );
            ps.setString( index++, toSave.getGyarto() );
            ps.setBoolean( index++, toSave.isEsim() );
            int rows = ps.executeUpdate();

            if ( rows != 1 ) { throw new RuntimeException("Valami"); }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
