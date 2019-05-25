package com.elo.oc.service;

import com.elo.oc.dao.SpotDAO;
import com.elo.oc.entity.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotDAO spotDAO;


    @Override
    public List<Spot> getSpots() {
        return spotDAO.getSpots();
    }

    @Override
    public List<Spot> search(String city, String county, String name, int nbrSecteurs) {
        return spotDAO.search(city, county, name, nbrSecteurs);
    }

    @Override
    public void saveSpot(Spot spot) {
        spotDAO.saveSpot(spot);
    }

    @Override
    public void updateSpot(Spot spot) {
        spotDAO.updateSpot(spot);
    }

    @Override
    public void deleteSpot(Integer id) {
        spotDAO.deleteSpot(id);
    }

    @Override
    public Spot findSpotByName(String name) {
        return spotDAO.findSpotByName(name);
    }

    @Override
    public Spot findSpotByCounty(String county) {
        return spotDAO.findSpotByCounty(county);
    }

    @Override
    public Spot findSpotByCity(String city) {
        return spotDAO.findSpotByCity(city);
    }

    @Override
    public Spot findSpotWithAllInfosById(Integer id) {
        return spotDAO.findSpotWithAllInfosById(id);
    }
    @Override
    public Spot findSpotById(Integer id) {
        return spotDAO.findSpotById(id);
    }

    @Override
    public List<Spot> findSpotByUserId(Integer id){
        return spotDAO.findSpotByUserId(id);
    }

    @Override
    public Optional<Spot> findSpotWithThisName(String name) {
        return spotDAO.getSpots().stream()
                .filter(spot -> spot.getName().equals(name))
                .findFirst();
    }
}

