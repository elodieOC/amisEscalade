package com.elo.oc.dao;

import com.elo.oc.entity.Spot;

import java.util.List;

public interface SpotDAO {

    List <Spot> getSpots();

    void saveSpot(Spot spot);
    void deleteSpot(Integer id);

    Spot findSpotByName(String name);
    Spot findSpotByCounty(String county);
    Spot findSpotByCity(String city);
    Spot findSpotById(Integer id);
    List<Spot> findSpotByUserId(Integer id);

}