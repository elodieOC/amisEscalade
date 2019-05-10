package com.elo.oc.service;

import com.elo.oc.entity.Sector;

import java.util.List;

public interface SectorService {

    List <Sector> getSectors();

    void saveSector(Sector theSector);
    void updateSector(Sector theSector);
    void deleteSector(Integer id);

    Sector findSectorByName(String name);
    Sector findSectorById(Integer id);
    List<Sector> findSectorByUserId(Integer id);
    List<Sector> findSectorBySpotId(Integer id);

}