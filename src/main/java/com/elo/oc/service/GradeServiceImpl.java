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
    public void deleteGrade(int id) {
        gradeDAO.deleteGrade(id);
    }

    @Override
    public List<Grade> getGrades() {
        return gradeDAO.getGrades();
    }

    @Override
    public Grade findById(int id) {
        return gradeDAO.findById(id);
    }
}