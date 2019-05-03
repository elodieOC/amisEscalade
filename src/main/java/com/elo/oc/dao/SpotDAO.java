package com.elo.oc.dao;

import com.elo.oc.entity.Spot;

import java.util.List;

public interface SpotDAO {

    List <Spot> getSpots();

    void saveSpot(Spot spot);
    void deleteSpot(int id);

    Spot findByNameSpot(String name);
    Spot findByCountySpot(String region);
    Spot findByCitySpot(String city);
    Spot findByIdSpot(int id);
    List<Spot> findByUserId(int id);

}