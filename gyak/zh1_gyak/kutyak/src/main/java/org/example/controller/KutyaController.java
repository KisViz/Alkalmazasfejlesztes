package org.example.controller;

import org.example.dao.KutyaDAO;
import org.example.dao.KutyaDAOImpl;
import org.example.model.Kutya;

import java.util.List;

public class KutyaController {
    private final KutyaDAO dao;
    private static KutyaController single_instance = null;

    public KutyaController() {
        dao = new KutyaDAOImpl();
    }

    public static KutyaController getInstance() {
        if (single_instance == null) {
            single_instance = new KutyaController();
        }
        return single_instance;
    }

    public List<Kutya> findAll() {
        return dao.findAll();
    }



        public boolean add(Kutya kutya) {
            return dao.add(kutya);
        }

    }
