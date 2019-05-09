package com.elo.oc.dao;

import com.elo.oc.entity.Rating;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RatingDAOImpl implements RatingDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveRating(Rating theRating) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theRating);
    }

    @Override
    public void deleteRating(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(id);

    }

    @Override
    public List<Rating> getRatings() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< Rating > cq = cb.createQuery(Rating.class);
        Root< Rating > root = cq.from(Rating.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Rating findById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Rating theRating = currentSession.get(Rating.class, id);
        Hibernate.initialize(theRating.getGrades());
        return theRating;
    }
}
