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
    public void saveSpot(Spot spot) {
        spotDAO.saveSpot(spot);
    }

    @Override
    public void deleteSpot(int id) {
        spotDAO.deleteSpot(id);
    }

    @Override
    public Spot findByNameSpot(String name) {
        return spotDAO.findByNameSpot(name);
    }

    @Override
    public Spot findByRegionSpot(String region) {
        return spotDAO.findByCountySpot(region);
    }

    @Override
    public Spot findByCitySpot(String city) {
        return spotDAO.findByCitySpot(city);
    }

    @Override
    public Spot findByIdSpot(int id) {
        return spotDAO.findByIdSpot(id);
    }

    @Override
    public List<Spot> findByUserId(int id){
        return spotDAO.findByUserId(id);
    }

    @Override
    public Optional<Spot> findSpotWithThisName(String name) {
        return spotDAO.getSpots().stream()
                .filter(spot -> spot.getName().equals(name))
                .findFirst();
    }
}

