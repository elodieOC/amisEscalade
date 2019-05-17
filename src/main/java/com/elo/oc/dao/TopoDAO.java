package com.elo.oc.dao;

import com.elo.oc.entity.Topo;

import java.util.List;

public interface TopoDAO {

    List <Topo> getTopos();

    void saveTopo(Topo topo);
    void updateTopo(Topo topo);
    void deleteTopo(Integer id);

    Topo findTopoById(Integer id);
    List<Topo>  findTopoByUserId(Integer id);
    List<Topo>  findTopoByCity(String city);
}