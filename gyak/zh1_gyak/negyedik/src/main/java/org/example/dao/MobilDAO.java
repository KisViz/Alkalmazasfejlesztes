package org.example.dao;

import org.example.model.Mobil;

import java.util.List;

public interface MobilDAO {
    boolean add(Mobil mobil);
    List<Mobil> find(Mobil filter);
}
