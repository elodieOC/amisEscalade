package com.elo.oc.dao;

import com.elo.oc.entity.Route;

import java.util.List;

public interface RouteDAO {

    List <Route> getRoutes();

    void saveRoute(Route route);
    void updateRoute(Route route);
    void deleteRoute(Integer id);

    Route findRouteById(Integer id);
    List<Route> findRouteBySectorId(Integer id);


}