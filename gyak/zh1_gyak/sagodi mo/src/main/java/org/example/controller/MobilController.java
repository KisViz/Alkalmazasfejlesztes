package org.example.controller;

import org.example.dao.MobilDAO;
import org.example.dao.MobilSQLite;
import org.example.model.Mobil;

import java.util.List;

public class MobilController {

    private MobilDAO dao = new MobilSQLite();

    public List<Mobil> findAll() { return dao.findAll(); }
    public boolean save( Mobil m ) { return dao.save( m ); }
}
