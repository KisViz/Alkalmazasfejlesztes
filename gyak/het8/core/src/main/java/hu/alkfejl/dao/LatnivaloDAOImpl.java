package hu.alkfejl.dao;

import hu.alkfejl.model.Latnivalo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LatnivaloDAOImpl implements LatnivaloDAO {
    private static LatnivaloDAOImpl object = new LatnivaloDAOImpl(); // eager
    public static LatnivaloDAOImpl getInstance() { return object; }

    private List<Latnivalo> inMemoryDB;
    private LatnivaloDAOImpl() {
        inMemoryDB = new ArrayList<>();
    }

    @Override
    public boolean add(Latnivalo latnivalo) {
        inMemoryDB.add(latnivalo);
        return true;
    }

    @Override
    public List<Latnivalo> find(Latnivalo filter) {
        return inMemoryDB.stream()
                .filter(latnivalo -> filter.getNev() == null || latnivalo.getNev().equals(filter.getNev()))
                .filter(latnivalo -> filter.getAr() == -1 || latnivalo.getAr() == filter.getAr())
                .filter(latnivalo -> filter.getMettol() == -1 || latnivalo.getMettol() == filter.getMettol())
                .filter(latnivalo -> filter.getMeddig() == -1 || latnivalo.getMeddig() == filter.getMeddig())
                .filter(latnivalo -> filter.getLeiras() == null || latnivalo.getLeiras().equals(filter.getLeiras()))
                .filter(latnivalo -> filter.getNepszeruseg() == -1 || latnivalo.getNepszeruseg() == filter.getNepszeruseg())
                .collect(Collectors.toList());
    }
}
