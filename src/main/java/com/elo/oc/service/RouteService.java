package com.elo.oc.service;

import com.elo.oc.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RouteService {
    List<Route> getRoutes();

    void saveRoute(Route route);
    void updateRoute(Route route);
    void deleteRoute(Integer id);

    Route findRouteById(Integer id);
    List<Route> findRouteBySectorId(Integer id);

    String getRouteGradeMax(Integer id);
    String getRouteGradeMin(Integer id);
}
