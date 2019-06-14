package com.elo.oc.service;

import com.elo.oc.dao.SpotDAO;
import com.elo.oc.entity.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotDAO spotDAO;
    @Autowired
    private GradeService gradeService;

    @Override
    public List<Spot> getSpots() {
        return spotDAO.getSpots();
    }

    @Override
    public List<Spot> search(String city, String county, String name, Integer nbrSectors, String username, String cotMin, String cotMax) {
        List<Spot> spots = spotDAO.search(city, county, name,  username);

        List<Spot> filteredSpots = new ArrayList<>(spots);
        Iterator itr = filteredSpots.iterator();
        while (itr.hasNext())
        {
            boolean bool = true;

            Spot s = (Spot) itr.next();
            if(nbrSectors != null && s.getSectors().size() != nbrSectors){
                bool = false;
            }
            if(cotMin.length()!=0){
                Integer spotGradeMin = s.getGradeMinId();
                Integer minGradeSearchForm = Integer.parseInt(cotMin);
                if(spotGradeMin == 0){
                    bool = false;
                }
                else if(minGradeSearchForm != spotGradeMin){
                    if(minGradeSearchForm > spotGradeMin){
                        bool = false;
                    }
                }
                System.out.println(minGradeSearchForm+" < "+spotGradeMin);
                System.out.println("BOOL: "+bool);
            }
            if(cotMax.length() != 0){
                Integer spotGradeMax = s.getGradeMaxId();
                if(spotGradeMax == 0){
                    bool = false;
                }
                Integer maxGradeSearchForm = Integer.parseInt(cotMax);
                if(maxGradeSearchForm < spotGradeMax){
                    bool = false;
                }
                System.out.println(maxGradeSearchForm+" < "+spotGradeMax);
                System.out.println("BOOL: "+bool);
            }
            if (!bool) {
                itr.remove();
            }
        }
        return  filteredSpots;
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

    @Override
    public void displayGrade(List<Spot> theSpots) {
        for (Spot s : theSpots) {
            if (s.getGradeMaxId() != 0) {
                s.setGradeMax(gradeService.findGradeById(s.getGradeMaxId()).getName());
            } else {
                s.setGradeMax("n/a");
            }

            if(s.getGradeMinId() != 0) {
                s.setGradeMin(gradeService.findGradeById(s.getGradeMinId()).getName());
            }
            else{
                s.setGradeMin("n/a");
            }
        }
    }
}

