package org.example.dao;

import org.example.model.Kutya;

import java.util.List;

public interface KutyaDAO {
    boolean add(Kutya kutya);
    List<Kutya> findAll();
}
