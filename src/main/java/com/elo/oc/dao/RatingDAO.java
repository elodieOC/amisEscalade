package com.elo.oc.dao;

import com.elo.oc.entity.Rating;

import java.util.List;

public interface RatingDAO {

    void saveRating(Rating theRating);
    void deleteRating(int id);

    List < Rating > getRatings();
    Rating findById(int id);





}
