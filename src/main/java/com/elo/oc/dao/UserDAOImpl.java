package com.elo.oc.dao;

import com.elo.oc.entity.*;
import com.elo.oc.service.RoleService;
import com.elo.oc.utils.Encryption;
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
public class UserDAOImpl implements UserDAO {


    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RoleService roleService;

    @Override
    public List < User > getUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery < User > cq = cb.createQuery(User.class);
        Root < User > root = cq.from(User.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveUser(User user){
        Session currentSession = sessionFactory.getCurrentSession();
        user.setPassword(Encryption.encrypt(user.getPassword()));


        user.setUserRole(roleService.findById(user.getMemberOrNot()));
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void adminSaveUser(User user){
        Session currentSession = sessionFactory.getCurrentSession();

        user.setUserRole(roleService.findById(user.getMemberOrNot()));
        currentSession.update(user);
    }

    @Override
    public void deleteUser(Integer id){
        Session session = sessionFactory.getCurrentSession();
        User u = session.byId(User.class).load(id);
        for (Spot s: u.getSpots() ) {
            s.setUser(findUserByUsername("deleted"));
            session.saveOrUpdate(s);
        }
        session.delete(u);
    }


    @Override
    public User findUserByEmail(String email){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findUserByEmail", User.class);
        query.setParameter("email", email);
        User result = query.getSingleResult();
        return result;
    }

    @Override
    public User findUserByUsername(String username){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findUserByUserName", User.class);
        query.setParameter("username", username);
        User result = query.getSingleResult();
        return result;
    }

    @Override
    public User findUserById(Integer id){
        Session currentSession = sessionFactory.getCurrentSession();
        User theUser = currentSession.get(User.class, id);
        return theUser;
    }

   @Override
    public User findUserByIdWithAllInfos(Integer id){
        Session currentSession = sessionFactory.getCurrentSession();

       User theUser = currentSession.get(User.class, id);
        Hibernate.initialize(theUser.getSpots());
        Hibernate.initialize(theUser.getComments());
        /*
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery q = cb.createQuery(User.class);
        Root o = q.from(User.class);
        o.fetch("spots", JoinType.INNER);
        q.select(o);
        q.where(cb.equal(o.get("id"), id));

        User theUser = (User)currentSession.createQuery(q).getSingleResult();
        */
        return theUser;
    }

    @Override
    public List<User> findUserByRole(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findUserByRole", User.class);
        query.setParameter("role", id);
        List<User> result = query.getResultList();
        return result;
    }
}
