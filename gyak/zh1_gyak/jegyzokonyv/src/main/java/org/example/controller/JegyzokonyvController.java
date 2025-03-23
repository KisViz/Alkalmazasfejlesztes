package org.example.controller;

import org.example.dao.JegyzokonyvDAO;
import org.example.dao.JegyzokonyvDAOImpl;
import org.example.model.Jegyzokonyv;

import java.util.List;

public class JegyzokonyvController {
    private JegyzokonyvDAO dao;
    private static JegyzokonyvController single_instance = null;

    public JegyzokonyvController() {
        dao = new JegyzokonyvDAOImpl();
    }

    public static JegyzokonyvController getInstance() {
        if (single_instance == null) {
            single_instance = new JegyzokonyvController();
        }

        return single_instance;
    }

    public List<Jegyzokonyv> find(Jegyzokonyv filter) {
        return dao.find(filter);
    }

    public boolean add(Jegyzokonyv jegyzokonyv) {
        if (find(jegyzokonyv).size() == 0) {
            return dao.add(jegyzokonyv);
        }
        return false;
    }
}
