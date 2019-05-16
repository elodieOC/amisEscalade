package com.elo.oc.service;

import com.elo.oc.dao.LengthDAO;
import com.elo.oc.entity.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LengthServiceImpl implements LengthService{

    @Autowired
    private LengthDAO lengthDAO;

    @Override
    public List<Length> getLengths() {
        return lengthDAO.getLengths();
    }

    @Override
    public void saveLength(Length length) {
        lengthDAO.saveLength(length);
    }

    @Override
    public void updateLength(Length length) {
        lengthDAO.updateLength(length);
    }

    @Override
    public void deleteLength(Integer id) {
        lengthDAO.deleteLength(id);
    }

    @Override
    public Length findLengthById(Integer id) {
        return lengthDAO.findLengthById(id);
    }
}
