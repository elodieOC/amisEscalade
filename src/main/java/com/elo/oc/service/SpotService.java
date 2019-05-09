package com.elo.oc.service;

import com.elo.oc.entity.Spot;

import java.util.List;
import java.util.Optional;

public interface SpotService {

    public List <Spot> getSpots();

    void saveSpot(Spot spot);
    void deleteSpot(Integer id);

    Spot findSpotByName(String name);
    Spot findSpotByCounty(String county);
    Spot findSpotByCity(String city);
    Spot findSpotById(Integer id);
    Optional<Spot> findSpotWithThisName(String name);

    List<Spot> findSpotByUserId(Integer id);
}