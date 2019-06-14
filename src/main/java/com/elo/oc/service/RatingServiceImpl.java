package com.elo.oc.service;

import com.elo.oc.dao.RatingDAO;
import com.elo.oc.entity.Grade;
import com.elo.oc.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingDAO ratingDAO;
    @Autowired
    private GradeService gradeService;

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
    public Rating findRatingById(Integer id) {
        return ratingDAO.findById(id);
    }

    @Override
    public void displayGradeList (List<Rating> theRatings){
        for (Rating r: theRatings){
            List<Grade> thisRatingGrades = gradeService.findGradeByRatingId(r.getId());
            List<String> gradeNames = new ArrayList<>();
            for (Grade g:thisRatingGrades){
                gradeNames.add(g.getName());
            }
            r.setGradeList(Arrays.toString(gradeNames.toArray()).replace("[", "").replace("]", ""));
        }
    }
}
