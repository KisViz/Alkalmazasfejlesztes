package hu.alkfejl.controller;

import hu.alkfejl.dao.UtazasDAO;
import hu.alkfejl.dao.UtazasDAOImpl;
import hu.alkfejl.dao.UtazasSQLite;
import hu.alkfejl.model.Utazas;

import java.util.List;

import static java.lang.System.out;

public class UtazasController {

    private UtazasDAO dao;
    private static UtazasController single_instance = null;

    private UtazasController() {
        dao = new UtazasSQLite();
    }

    public static UtazasController getInstance(){
        if(single_instance == null){ // lazy
            // most nem probléma, de több szálon syncelni kell!
            single_instance = new UtazasController();
        }
        return single_instance;
    }

    public boolean add(Utazas utazas) {
        if ( find(utazas).size() == 0 )
            return dao.add(utazas);
        return false;
    }
    public List<Utazas> find(Utazas filter) {
        out.println("called");
        return dao.find(filter);
    }
}
