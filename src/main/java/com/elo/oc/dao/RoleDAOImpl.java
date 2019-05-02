package com.elo.oc.dao;

import com.elo.oc.entity.Role;
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
public class RoleDAOImpl implements RoleDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List < Role > getRoles() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery < Role > cq = cb.createQuery(Role.class);
        Root < Role > root = cq.from(Role.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveRole(Role theRole){
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theRole);
    }

    @Override
    public void deleteRole(int id){
        Session session = sessionFactory.getCurrentSession();
        Role u = session.byId(Role.class).load(id);
        session.delete(u);
    }


    @Override
    public Role findById(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Role theRole = currentSession.get(Role.class, id);
        return theRole;
    }


}
