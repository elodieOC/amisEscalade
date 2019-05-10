package com.elo.oc.dao;

import com.elo.oc.entity.Sector;

import java.util.List;

public interface SectorDAO {

    List <Sector> getSectors();

    void saveSector(Sector sector);
    void updateSector(Sector sector);
    void deleteSector(Integer id);

    Sector findSectorByName(String name);
    Sector findSectorById(Integer id);
    List<Sector> findSectorByUserId(Integer id);
    List<Sector> findSectorBySpotId(Integer id);

}