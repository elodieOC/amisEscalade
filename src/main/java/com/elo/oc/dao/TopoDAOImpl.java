package com.elo.oc.dao;

import com.elo.oc.entity.Topo;
import com.elo.oc.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * <h2>DB links for Topo entity</h2>
 */
@Repository
public class TopoDAOImpl implements TopoDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserService userService;

    /**
     * <p>queries all topos in db</p>
     * @return list of all topos in db
     */
    @Override
    public List<Topo> getTopos() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Topo> cq = cb.createQuery(Topo.class);
        Root<Topo> root = cq.from(Topo.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    /**
     * <p>creates a topo in db</p>
     * @param topo topo being created
     */
    @Override
    public void saveTopo(Topo topo) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(topo);
    }

    /**
     * <p>updates a topo in db</p>
     * @param topo topo being updated
     */
    @Override
    public void updateTopo(Topo topo) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(topo);
    }

    /**
     * <p>deletes a topo from db</p>
     * @param id id of the topo being deleted
     */
    @Override
    public void deleteTopo(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Topo theTopo = session.byId(Topo.class).load(id);
        session.delete(theTopo);
    }

    /**
     * <p>queries for a particular topo with a specific id</p>
     * @param id id of the topo being searched for
     * @return the topo with the id in param
     */
    @Override
    public Topo findTopoById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Topo theTopo = currentSession.get(Topo.class, id);
        return theTopo;
    }

    /**
     * <p>queries for topos owned by a user</p>
     * @param id id of the user
     * @return a list of topos owned by the user with the id in param
     */
    @Override
    public List<Topo> findTopoByUserId(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery("findTopoByUserId", Topo.class);
        query.setParameter("climb_user_fk", id);
        List<Topo> result = query.getResultList();
        return result;
    }

    /**
     * <p>queries for topos located in a specific city</p>
     * @param city being queried on
     * @return a list of topos located in the city in param
     */
    @Override
    public List<Topo>  findTopoByCity(String city) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Topo> query = currentSession.createNamedQuery("findTopoByCity", Topo.class);
        query.setParameter("city", city);
        List<Topo> result = query.getResultList();
        return result;
    }
}
