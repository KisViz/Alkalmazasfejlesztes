package org.example.dao;

import org.example.model.Jegyzokonyv;

import java.util.List;

public interface JegyzokonyvDAO {
    boolean add(Jegyzokonyv jegyzokonyv);
    List<Jegyzokonyv> find(Jegyzokonyv filter);
}
