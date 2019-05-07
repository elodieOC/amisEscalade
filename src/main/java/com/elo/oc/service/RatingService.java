package com.elo.oc.service;

import com.elo.oc.entity.Rating;

import java.util.List;

public interface RatingService {

    void saveRating(Rating theRating);
    void deleteRating(int id);

    List < Rating > getRatings();
    Rating findById(int id);





}
