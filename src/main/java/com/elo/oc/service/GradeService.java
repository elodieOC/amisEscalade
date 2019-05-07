package com.elo.oc.service;

import com.elo.oc.entity.Grade;

import java.util.List;

public interface GradeService {

    void saveGrade(Grade theGrade);
    void deleteGrade(int id);

    List < Grade > getGrades();
    Grade findById(int id);





}
