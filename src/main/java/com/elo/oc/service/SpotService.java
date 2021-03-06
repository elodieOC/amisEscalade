package com.elo.oc.service;

import com.elo.oc.entity.Spot;

import java.util.List;
import java.util.Optional;

public interface SpotService {

    List <Spot> getSpots();

    void saveSpot(Spot spot);
    void updateSpot(Spot spot);
    void deleteSpot(Integer id);

    Spot findSpotByName(String name);
    Spot findSpotByCounty(String county);
    Spot findSpotByCity(String city);
    Spot findSpotById(Integer id);
    Spot findSpotWithAllInfosById(Integer id);
    Optional<Spot> findSpotWithThisName(String name);

    List<Spot> findSpotByUserId(Integer id);
    List<Spot> search(String city, String county, String name, Integer nbrSectors, String username,  String cotMin, String cotMax);
    void displayGrade(List<Spot> theSpots);
}