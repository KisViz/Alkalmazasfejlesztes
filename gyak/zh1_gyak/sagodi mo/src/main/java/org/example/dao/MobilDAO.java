package org.example.dao;

import org.example.model.Mobil;

import java.util.List;

public interface MobilDAO {
    List<Mobil> findAll();
    boolean save( Mobil toSave );
}
