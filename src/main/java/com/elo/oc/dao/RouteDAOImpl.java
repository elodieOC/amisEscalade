package com.elo.oc.dao;

import com.elo.oc.entity.Route;
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
public class RouteDAOImpl implements RouteDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Route> getRoutes() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Route> cq = cb.createQuery(Route.class);
        Root<Route> root = cq.from(Route.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveRoute(Route route) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(route);
    }

    @Override
    public void updateRoute(Route route) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(route);
    }

    @Override
    public void deleteRoute(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Route theRoute = session.byId(Route.class).load(id);
        session.delete(theRoute);
    }

    @Override
    public Route findRouteById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Route theRoute = currentSession.get(Route.class, id);
        return theRoute;
    }

    @Override
    public List<Route> findRouteBySectorId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Route> query = currentSession.createNamedQuery("findRouteBySectorId", Route.class);
        query.setParameter("sectorId", id);
        List<Route> routes = query.getResultList();
        return routes;
    }
}
