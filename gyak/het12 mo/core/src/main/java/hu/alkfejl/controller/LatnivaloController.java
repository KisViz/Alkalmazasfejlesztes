package hu.alkfejl.controller;

import hu.alkfejl.dao.LatnivaloDAO;
import hu.alkfejl.dao.LatnivaloDAOImpl;
import hu.alkfejl.model.Latnivalo;


import java.util.List;

public class LatnivaloController {

    private LatnivaloDAO dao;
    private static LatnivaloController single_instance = null;

    private LatnivaloController() {
        dao = LatnivaloDAOImpl.getInstance();
    }

    public static LatnivaloController getInstance(){
        if(single_instance == null){ // lazy
            // most nem probléma, de több szálon syncelni kell!
            single_instance = new LatnivaloController();
        }
        return single_instance;
    }

    public boolean add(Latnivalo latnivalo) {
        if ( find(latnivalo).size() == 0 )
            return dao.add(latnivalo);
        return false;
    }
    public List<Latnivalo> find(Latnivalo filter) {
        return dao.find(filter);
    }
}
