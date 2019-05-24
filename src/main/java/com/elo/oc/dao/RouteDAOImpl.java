package com.elo.oc.dao;

import com.elo.oc.entity.Grade;
import com.elo.oc.entity.Length;
import com.elo.oc.entity.Route;
import com.elo.oc.service.GradeService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
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
        cq.select(root).distinct(true);
        root.fetch("lengths", JoinType.INNER);
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
        for(Length l: theRoute.getLengths()){
            session.remove(l);
        }
        session.delete(theRoute);
    }

    @Override
    public Route findRouteById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Route theRoute = currentSession.get(Route.class, id);
        Hibernate.initialize(theRoute.getLengths());
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

    @Override
    public List<Route> findRouteBySpotId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Route> query = currentSession.createNamedQuery("findRouteBySpotId", Route.class);
        query.setParameter("spotId", id);
        List<Route> routes = query.getResultList();
        return routes;
    }

    @Override
    public String getRouteGradeMin(Integer id){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery("findLengthByRouteId", Length.class);
        query.setParameter("routeId", findRouteById(id));
        List<Length>lengths = query.getResultList();

        int min = 0;
        for (Length l:lengths) {
            int lengthGrade = l.getGrade().getId();
            if (lengthGrade <= min || min == 0) {
                min = lengthGrade;
            }
        }
        Route route = findRouteById(id);

        Grade grade = currentSession.get(Grade.class, min);
        route.setGradeMin(grade.getName());
        return grade.getName();
    }
    @Override
    public String getRouteGradeMax(Integer id){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Length> query = currentSession.createNamedQuery("findLengthByRouteId", Length.class);
        query.setParameter("routeId", findRouteById(id));
        List<Length>lengths = query.getResultList();

        int max = 0;
        for (Length l:lengths) {
            int lengthGrade = l.getGrade().getId();
            if (lengthGrade >= max) {
                max = lengthGrade;
            }
        }
        Route route = findRouteById(id);
        Grade grade = currentSession.get(Grade.class, max);
        route.setGradeMax(grade.getName());
        return grade.getName();
    }

}
