package com.elo.oc.service;

import com.elo.oc.dao.RouteDAO;
import com.elo.oc.entity.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteDAO routeDAO;

    @Override
    public List<Route> getRoutes() {
        return routeDAO.getRoutes();
    }

    @Override
    public void saveRoute(Route route) {
        routeDAO.saveRoute(route);
    }

    @Override
    public void updateRoute(Route route) {
        routeDAO.updateRoute(route);
    }

    @Override
    public void deleteRoute(Integer id) {
        routeDAO.deleteRoute(id);
    }

    @Override
    public Route findRouteById(Integer id) {
        return routeDAO.findRouteById(id);
    }

    @Override
    public List<Route> findRouteBySectorId(Integer id) {
        return routeDAO.findRouteBySectorId(id);
    }

    @Override
    public String getRouteGradeMax(Integer id) {
        return routeDAO.getRouteGradeMax(id);
    }

    @Override
    public List<Route> findRouteBySpotId(Integer id) {
        return routeDAO.findRouteBySpotId(id);
    }

    @Override
    public String getRouteGradeMin(Integer id) {
        return routeDAO.getRouteGradeMin(id);
    }
}
