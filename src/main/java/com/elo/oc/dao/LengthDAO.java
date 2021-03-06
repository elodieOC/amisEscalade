package com.elo.oc.dao;

import com.elo.oc.entity.Length;

import java.util.List;

public interface LengthDAO {

    List <Length> getLengths();

    void saveLength(Length length);
    void updateLength(Length length);
    void deleteLength(Integer id);

    Length findLengthById(Integer id);
    List<Length> findLengthByRouteId(Integer id);
}