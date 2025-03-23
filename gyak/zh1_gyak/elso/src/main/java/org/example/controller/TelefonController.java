package org.example.controller;

import org.example.dao.ContactDAOImpl;
import org.example.dao.TelefonDAO;
import org.example.model.Telefon;

import java.util.List;

public class TelefonController {
    private TelefonDAO dao;
    private static TelefonController single_instance = null;

    public TelefonController() {
        dao = new ContactDAOImpl();
    }

    public static TelefonController getInstance() {
        if (single_instance == null) {
            single_instance = new TelefonController();
        }
        return single_instance;
    }

    public List<Telefon> find(Telefon filter) {
        System.out.println("hivva");
        return dao.find(filter);
    }

    public boolean add(Telefon telefon) {
        if (find(telefon).size() == 0) {
            return dao.add(telefon);
        }
        return false;
    }
}
