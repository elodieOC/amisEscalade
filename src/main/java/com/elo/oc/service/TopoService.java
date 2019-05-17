package com.elo.oc.service;

import com.elo.oc.entity.Topo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoService {
    List<Topo> getTopos();

    void saveTopo(Topo topo);
    void updateTopo(Topo topo);
    void deleteTopo(Integer id);

    Topo findTopoById(Integer id);
    List<Topo>  findTopoByUserId(Integer id);
    List<Topo>  findTopoByCity(String city);
}
