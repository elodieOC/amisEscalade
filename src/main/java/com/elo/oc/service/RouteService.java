package com.elo.oc.service;

import com.elo.oc.entity.Route;

import java.util.List;

public interface RouteService {
    List<Route> getRoutes();

    void saveRoute(Route route);
    void updateRoute(Route route);
    void deleteRoute(Integer id);

    Route findRouteById(Integer id);
    List<Route> findRouteBySectorId(Integer id);
    List<Route> findRouteBySpotId(Integer id);

    String getRouteGradeMax(Integer id);
    String getRouteGradeMin(Integer id);
}
