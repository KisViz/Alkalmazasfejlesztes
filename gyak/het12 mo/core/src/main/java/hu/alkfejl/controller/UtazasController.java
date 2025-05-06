package hu.alkfejl.controller;

import hu.alkfejl.dao.UtazasDAO;
import hu.alkfejl.dao.UtazasSQLiteImpl;
import hu.alkfejl.model.Utazas;
import hu.alkfejl.dao.UtazasDAOImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.System.out;

public class UtazasController {

    private UtazasDAO dao;
    // fontos, hogy interface alapjan egy valtozot hasznalkjunk DAO elereshez
    // igy konnyen tudjuk valtoztatni, illetve
    // dinamikusan tudunk betolteni dao megvalositast
    private static final Map<String, UtazasController> instances = new ConcurrentHashMap<>();

    private UtazasController(String daoToUse, String dbPath) {
        switch(daoToUse) {
            case "memory": dao = UtazasDAOImpl.getInstance(); break;
            case "sqlite": dao = new UtazasSQLiteImpl(dbPath); break;
            default:
                try {
                    out.println("Using custom dao: " + daoToUse);
                    // barhogy megadhatjuk a peldanyositast, de fontos, hogy onnantol azt kell kovese az egyedi megvalositas is
                    // igy egy konstuktoron keresztul barmilyen metodus hivhato, erre figyeljunk oda!
                    // ez csak egy pelda, hogy lassuk a dao valtozo interface tipusanak fontossagat!
                    Class<?> c = Class.forName(daoToUse);
                    dao = (UtazasDAO) c.getDeclaredConstructor(String.class).newInstance(dbPath);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                         InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public static UtazasController getInstance(String daoTOUse, String dbPath){
        UtazasController instance;

        if ( instances.containsKey(daoTOUse + dbPath) ) {
            instance = instances.get(daoTOUse + dbPath);
        } else {
            instance = new UtazasController(daoTOUse, dbPath);
            instances.put(daoTOUse + dbPath, instance);
        }

        return instance;
    }

    public boolean add(Utazas utazas) {
        if (find(utazas).isEmpty())
            return dao.add(utazas);
        return false;
    }
    public List<Utazas> find(Utazas filter) {
        return dao.find(filter);
    }

    public boolean update(Utazas utazas) {
        return dao.update(utazas);
    }
    public boolean delete(Utazas utazas) { return dao.delete(utazas); }
    public void preferences(Utazas utazas) { dao.preferences(utazas); }
}
