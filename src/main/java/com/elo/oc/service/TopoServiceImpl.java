package com.elo.oc.service;

import com.elo.oc.dao.TopoDAO;
import com.elo.oc.entity.Topo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopoServiceImpl implements TopoService {
    @Autowired
    private TopoDAO topoDAO;

    @Override
    public List<Topo> getTopos() {
        return topoDAO.getTopos();
    }

    @Override
    public void saveTopo(Topo topo) {
        topoDAO.saveTopo(topo);
    }

    @Override
    public void updateTopo(Topo topo) {
        topoDAO.updateTopo(topo);
    }

    @Override
    public void deleteTopo(Integer id) {
        topoDAO.deleteTopo(id);
    }

    @Override
    public Topo findTopoById(Integer id) {
        return topoDAO.findTopoById(id);
    }

    @Override
    public List<Topo> findTopoByUserId(Integer id) {
        return topoDAO.findTopoByUserId(id);
    }

    @Override
    public List<Topo> findTopoByCity(String city) {
        return topoDAO.findTopoByCity(city);
    }
}
