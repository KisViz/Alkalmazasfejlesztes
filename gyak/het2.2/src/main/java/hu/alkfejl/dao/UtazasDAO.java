package hu.alkfejl.dao;

import hu.alkfejl.model.Utazas;

import java.util.List;

public interface UtazasDAO {

    boolean add(Utazas utazas);
    List<Utazas> find(Utazas utazas);
}
