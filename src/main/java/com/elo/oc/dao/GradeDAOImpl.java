package com.elo.oc.dao;

import com.elo.oc.entity.Grade;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GradeDAOImpl implements GradeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveGrade(Grade theGrade) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theGrade);
    }

    @Override
    public void deleteGrade(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(id);

    }

    @Override
    public Grade findGradeByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Grade> query = currentSession.createNamedQuery("findGradeByName", Grade.class);
        query.setParameter("name", name);
        Grade result = query.getSingleResult();
        return result;
    }

    @Override
    public List<Grade> getGrades() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Grade > cq = cb.createQuery(Grade.class);
        Root< Grade > root = cq.from(Grade.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Grade findById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Grade theGrade = currentSession.get(Grade.class, id);
        return theGrade;
    }
}
