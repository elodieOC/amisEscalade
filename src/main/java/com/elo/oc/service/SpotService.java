package com.elo.oc.service;

import com.elo.oc.entity.Spot;

import java.util.List;
import java.util.Optional;

public interface SpotService {

    public List <Spot> getSpots();

    void saveSpot(Spot spot);
    void deleteSpot(int id);

    Spot findByNameSpot(String name);
    Spot findByRegionSpot(String region);
    Spot findByCitySpot(String city);
    Spot findByIdSpot(int id);
    Optional<Spot> findSpotWithThisName(String name);

    List<Spot> findByUserId(int id);
}