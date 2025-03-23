package hu.alkfejl.dao;

import hu.alkfejl.model.Utazas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class UtazasDAOImpl implements UtazasDAO {

    private static UtazasDAOImpl object = new UtazasDAOImpl();
    public static UtazasDAOImpl getInstance() { return object; }

    private List<Utazas> inMemoryDB;
    private UtazasDAOImpl() {
        inMemoryDB = new ArrayList<>();
    }
    @Override
    public boolean add(Utazas utazas) {
        inMemoryDB.add( utazas );
        return true;
    }

    @Override
    public List<Utazas> find(Utazas filter) {

        var res = inMemoryDB.stream()
                .filter(utazas -> filter.getNev() == null || utazas.getNev().equals(filter.getNev()))
                .filter(utazas -> filter.getUticel() == null || utazas.getUticel().equals(filter.getUticel()))
                //.filter(utazas -> filter.getFelpanzio() == null || utazas.getFelpanzio().equals(filter.getFelpanzio()))
                .filter(utazas -> filter.getUtasok() == 0 || utazas.getUtasok() == filter.getUtasok())
                .filter(utazas -> filter.getEjszaka() == 0 || utazas.getEjszaka() == filter.getEjszaka())
                .filter(utazas -> filter.getLeiras() == null || utazas.getLeiras().equals(filter.getLeiras()))
                .collect(Collectors.toList());
        for ( Utazas u : res ) {
            out.println( u.getNev());
        }
        return res;
    }
}
