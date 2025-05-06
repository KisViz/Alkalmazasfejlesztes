package hu.alkfejl.dao;

import hu.alkfejl.model.Latnivalo;

import java.util.List;

public interface LatnivaloDAO {
    boolean add(Latnivalo latnivalo);
    List<Latnivalo> find(Latnivalo filter);
}
