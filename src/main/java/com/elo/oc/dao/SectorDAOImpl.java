package com.elo.oc.dao;

import com.elo.oc.entity.Route;
import com.elo.oc.entity.Sector;
import org.hibernate.Hibernate;
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
public class SectorDAOImpl implements SectorDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Sector> getSectors() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Sector> cq = cb.createQuery(Sector.class);
        Root<Sector> root = cq.from(Sector.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveSector(Sector sector) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(sector);
    }

    @Override
    public void updateSector(Sector sector) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(sector);
    }

    @Override
    public void deleteSector(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Sector theSector = session.byId(Sector.class).load(id);
        for (Route r: theSector.getRoutes() ) {
            session.remove(r);
        }
        session.delete(theSector);
    }

    @Override
    public Sector findSectorByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery("findSectorByName", Sector.class);
        query.setParameter("name", name);
        Sector result = query.getSingleResult();
        return result;
    }

    @Override
    public Sector findSectorById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Sector theSector = currentSession.get(Sector.class, id);
        Hibernate.initialize(theSector.getRoutes());
        return theSector;
    }

    @Override
    public List<Sector> findSectorByUserId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery("findSectorByUserId", Sector.class);
        query.setParameter("userId", id);
        List<Sector> sectors = query.getResultList();
        return sectors;
    }

    @Override
    public List<Sector> findSectorBySpotId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Sector> query = currentSession.createNamedQuery("findSectorBySpotId", Sector.class);
        query.setParameter("spotId", id);
        List<Sector> sectors = query.getResultList();
        return sectors;
    }
}
