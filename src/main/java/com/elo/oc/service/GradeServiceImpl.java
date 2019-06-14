package com.elo.oc.service;

import com.elo.oc.dao.GradeDAO;
import com.elo.oc.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDAO gradeDAO;

    @Override
    public void saveGrade(Grade theGrade) {
        gradeDAO.saveGrade(theGrade);
    }

    @Override
    public void deleteGrade(Integer id) {
        gradeDAO.deleteGrade(id);
    }

    @Override
    public List<Grade> getGrades() {
        return gradeDAO.getGrades();
    }

    @Override
    public Grade findGradeByName(String name) {
        return gradeDAO.findGradeByName(name);
    }

    @Override
    public Grade findGradeById(Integer id) {
        return gradeDAO.findGradeById(id);
    }

    @Override
    public List<Grade> findGradeByRatingId(Integer id) {
        return gradeDAO.findGradeByRatingId(id);
    }
}
