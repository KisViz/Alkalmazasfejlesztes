package org.example.dao;

import org.example.model.Telefon;

import java.util.List;

public interface TelefonDAO {
        boolean add(Telefon telefon);
        List<Telefon> find(Telefon telefon);

}
