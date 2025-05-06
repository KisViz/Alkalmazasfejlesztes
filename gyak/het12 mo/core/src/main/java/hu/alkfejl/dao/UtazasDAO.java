package hu.alkfejl.dao;

import hu.alkfejl.model.Utazas;

import java.util.List;

public interface UtazasDAO {

    boolean add(Utazas utazas);
    List<Utazas> find(Utazas utazas);

    default boolean update(Utazas utazas) { return false; } // interface tartalmazhat default metodusokat, ez alatalaban akkor hasznos, ha visszafele kell kompatibilis legyen az uj interface.
    default boolean delete(Utazas utazas) { return false; }

    default void preferences(Utazas u) {}
}
