package com.elo.oc.dao;

import com.elo.oc.entity.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
    public List<Spot> search(String city, String county, String name, String username) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Spot> query = currentSession.createQuery("select distinct s from Spot s " +
                "left join fetch s.sectors sectors " +
                "left join fetch sectors.routes routes  " +
                "where(lower(s.name) like '%'||lower(:name)||'%' or s.name is null) " +
                "and (lower(s.city) like '%'||lower(:city)||'%' or s.city is null) " +
                "and (lower(s.county) like '%'||lower(:county)||'%' or s.county is null) ");

        query.setParameter("city", city);
        query.setParameter("county", county);
        query.setParameter("name", name);
/*
       String queryString = "select s from Spot s where 1=1 ";
       if(!StringUtils.isEmpty(city)){queryString+= "and s.city like '%'||:city||'%'";}
       Query<Spot> query = currentSession.createQuery(queryString);
       if(!StringUtils.isEmpty(city)){query.setParameter("city", city);}*/

        return query.getResultList();
    }

    public void capitalizeAttributes(Spot spot){
        spot.setName(StringUtils.capitalize(spot.getName()));
        spot.setCity(StringUtils.capitalize(spot.getCity()));
        spot.setCounty(StringUtils.capitalize(spot.getCounty()));
    }


    @Override
    public void saveSpot(Spot spot) {
        Session currentSession = sessionFactory.getCurrentSession();
        capitalizeAttributes(spot);
        currentSession.saveOrUpdate(spot);
    }

    @Override
    public void updateSpot(Spot spot) {
        Session currentSession = sessionFactory.getCurrentSession();
        capitalizeAttributes(spot);
        currentSession.update(spot);
    }

    @Override
    public void deleteSpot(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Spot theSpot = session.byId(Spot.class).load(id);
        for (Comment c: theSpot.getComments() ) {
            session.remove(c);
        }for (Sector s: theSpot.getSectors() ) {
            session.remove(s);
        }
        session.delete(theSpot);
    }

    @Override
    public Spot findSpotByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findSpotByName", Spot.class);
        query.setParameter("name", name);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findSpotByCounty(String county) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findSpotByCounty", Spot.class);
        query.setParameter("county", county);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findSpotByCity(String city) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findSpotByCity", Spot.class);
        query.setParameter("city", city);
        Spot result = query.getSingleResult();
        return result;
    }

    @Override
    public Spot findSpotWithAllInfosById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Spot theSpot = currentSession.get(Spot.class, id);
        Hibernate.initialize(theSpot.getComments());
        Hibernate.initialize((theSpot.getSectors()));
        return theSpot;
    }

    @Override
    public Spot findSpotById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Spot theSpot = currentSession.get(Spot.class, id);
        return theSpot;
    }

    @Override
    public List<Spot> findSpotByUserId(Integer id){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findSpotByUserId", Spot.class);
        query.setParameter("climb_user_fk", id);
        List <Spot> spots = query.getResultList();
        return spots;
    }
    @Override
    public List<Spot> findSpotWithOfficialTag(){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Spot> query = currentSession.createNamedQuery("findSpotWithOfficialTag", Spot.class);
        query.setParameter("tagged", true);
        List <Spot> spots = query.getResultList();
        return spots;
    }


}
