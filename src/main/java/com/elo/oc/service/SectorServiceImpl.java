package com.elo.oc.service;

import com.elo.oc.dao.SectorDAO;
import com.elo.oc.entity.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorDAO sectorDAO;

    @Override
    public List<Sector> getSectors() {
        return sectorDAO.getSectors();
    }

    @Override
    public void saveSector(Sector theSector) {
        sectorDAO.saveSector(theSector);
    }

    @Override
    public void updateSector(Sector theSector) {
        sectorDAO.updateSector(theSector);
    }

    @Override
    public void deleteSector(Integer id) {
sectorDAO.deleteSector(id);
    }

    @Override
    public Sector findSectorByName(String name) {
        return sectorDAO.findSectorByName(name);
    }

    @Override
    public Sector findSectorById(Integer id) {
        return sectorDAO.findSectorById(id);
    }

    @Override
    public List<Sector> findSectorByUserId(Integer id) {
        return sectorDAO.findSectorByUserId(id);
    }

    @Override
    public List<Sector> findSectorBySpotId(Integer id) {
        return sectorDAO.findSectorBySpotId(id);
    }

}
