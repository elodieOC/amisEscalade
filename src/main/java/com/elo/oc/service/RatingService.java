package com.elo.oc.service;

import com.elo.oc.entity.Rating;

import java.util.List;

public interface RatingService {

    void saveRating(Rating theRating);
    void deleteRating(Integer id);

    List < Rating > getRatings();
    Rating findRatingById(Integer id);
    void displayGradeList (List<Rating> theRatings);




}
