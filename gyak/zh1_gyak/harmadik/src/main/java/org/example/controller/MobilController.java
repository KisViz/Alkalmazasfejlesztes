package org.example.controller;

import org.example.dao.MobilDAO;
import org.example.dao.MobilDAOImpl;
import org.example.model.Mobil;

import java.util.List;

public class MobilController {
    private MobilDAO dao;
    private static MobilController single_instance = null;

    public MobilController() {
        dao = new MobilDAOImpl();
    }

    public static MobilController getInstance() {
        if (single_instance == null) {
            single_instance = new MobilController();
        }
        return single_instance;
    }

    public List<Mobil> find(Mobil filter) {
        return dao.find(filter);
    }

    public boolean add(Mobil mobil) {
        if (find(mobil).size() == 0) {
            return dao.add(mobil);
        }
        return false;
    }
}
