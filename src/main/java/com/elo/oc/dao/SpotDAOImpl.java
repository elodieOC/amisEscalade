package com.elo.oc.dao;

import com.elo.oc.entity.Spot;
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
public class SpotDAOImpl implements SpotDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List <Spot> getSpots() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery <Spot> cq = cb.createQuery(Spot.class);
        Root <Spot> root = cq.from(Spot.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveSpot(Spot spot) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(spot);
    }

    @Override
    public void deleteSpot(int id) {
        Session session = sessionFactory.getCurrentSession();
        Spot theSpot = session.byId(Spot.class).load(id);
        session.delete(theSpot);
    }

    @Override
    public Spot findByNameSpot(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findByName", Spot.class);
        query.setParameter("name", name);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findByCountySpot(String county) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findByCounty", Spot.class);
        query.setParameter("county", county);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findByCitySpot(String city) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findByCity", Spot.class);
        query.setParameter("city", city);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findByIdSpot(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Spot theSpot = currentSession.get(Spot.class, id);
        return theSpot;
    }

    @Override
    public List<Spot> findByUserId(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findByUserId", Spot.class);
        query.setParameter("climb_user_fk", id);
        List <Spot> spots = query.getResultList();
        return spots;
    }


}
