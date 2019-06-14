package com.elo.oc.service;

import com.elo.oc.entity.Grade;

import java.util.List;

public interface GradeService {

    void saveGrade(Grade theGrade);
    void deleteGrade(Integer id);

    List < Grade > getGrades();
    Grade findGradeById(Integer id);
    Grade findGradeByName(String name);
    List < Grade > findGradeByRatingId(Integer id);




}
