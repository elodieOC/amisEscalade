package com.elo.oc.dao;

import com.elo.oc.entity.Length;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LengthDAOImpl implements LengthDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Length> getLengths() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Length> cq = cb.createQuery(Length.class);
        Root<Length> root = cq.from(Length.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveLength(Length length) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(length);
    }

    @Override
    public void updateLength(Length length) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(length);
    }

    @Override
    public void deleteLength(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Length theLength = session.byId(Length.class).load(id);
        session.delete(theLength);
    }

    @Override
    public Length findLengthById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Length theLength = currentSession.get(Length.class, id);
        return theLength;
    }

    @Override
    public List<Length> findLengthByRouteId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery("findLengthByRouteId", Length.class);
        query.setParameter("routeId", id);
        List<Length> lengths = query.getResultList();
        return lengths;
    }
}
