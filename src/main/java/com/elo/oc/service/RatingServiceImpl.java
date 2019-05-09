package com.elo.oc.service;

import com.elo.oc.dao.RatingDAO;
import com.elo.oc.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingDAO ratingDAO;

    @Override
    public void saveRating(Rating theRating) {
        ratingDAO.saveRating(theRating);
    }

    @Override
    public void deleteRating(Integer id) {
        ratingDAO.deleteRating(id);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingDAO.getRatings();
    }

    @Override
    public Rating findById(Integer id) {
        return ratingDAO.findById(id);
    }
}
