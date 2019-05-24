package com.elo.oc.dao;

import com.elo.oc.entity.Comment;
import com.elo.oc.entity.Sector;
import com.elo.oc.entity.Spot;
import com.elo.oc.entity.User;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Spot> search(String city, String county, String name, String nbrSecteurs, String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        if(!city.equals("")){
            Query <Spot>  query = currentSession.createQuery("select s from Spot s where s.city like '%'||:city||'%' ", Spot.class);
            query.setParameter("city", city);
            return query.getResultList();
        }
        if(!username.equals("")){
            Query <Spot>  query = currentSession.createQuery("select s from Spot s where s.username like '%'||:username||'%' ", Spot.class);
            query.setParameter("username", username);
            return query.getResultList();
        }
        if(!county.equals("")){
            Query <Spot>  query = currentSession.createQuery("select s from Spot s where s.county like '%'||:county||'%' ", Spot.class);
            query.setParameter("county", county);
            return query.getResultList();
        }
        if(!name.equals("")){
            Query <Spot>  query = currentSession.createQuery("select s from Spot s where s.name like '%'||:name||'%' ", Spot.class);
            query.setParameter("name", name);
            return query.getResultList();
        }
        if(!name.equals("")){
            Query <Spot>  query = currentSession.createQuery("select s from Spot s where s.nbrSecteurs like '%'||:nbrSecteurs||'%' ", Spot.class);
            query.setParameter("nbrSecteurs", nbrSecteurs);
            return query.getResultList();
        }
        return getSpots();
    }

    @Override
    public void saveSpot(Spot spot) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(spot);
    }

    @Override
    public void updateSpot(Spot spot) {
        Session currentSession = sessionFactory.getCurrentSession();
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
