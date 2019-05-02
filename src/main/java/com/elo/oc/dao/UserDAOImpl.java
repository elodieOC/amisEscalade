package com.elo.oc.dao;

import com.elo.oc.entity.*;
import com.elo.oc.utils.Encryption;
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

        Role userRole = user.getUserRole();

        switch (userRole.getRoleName()){
            case "admin":
                UserAdmin userAdmin = new UserAdmin();
                userAdmin.setUser(user);
                currentSession.saveOrUpdate(userAdmin);
                break;
            case "membre asso":
                UserAssoMember userAssoMember = new UserAssoMember();
                userAssoMember.setUser(user);
                currentSession.saveOrUpdate(userAssoMember);
                break;
            case "non membre":
                UserNonMember userNonMember = new UserNonMember();
                userNonMember.setUser(user);
                currentSession.saveOrUpdate(userNonMember);
                break;
                default:
                    break;
        }
        currentSession.saveOrUpdate(user);
    }

    @Override
    public void deleteUser(int id){
        Session session = sessionFactory.getCurrentSession();
        User u = session.byId(User.class).load(id);
        session.delete(u);
    }


    @Override
    public User findByEmail(String email){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findByEmail", User.class);
        query.setParameter("email", email);
        User result = query.getSingleResult();
        return result;
    }

    @Override
    public User findByUsername(String username){
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findByUserName", User.class);
        query.setParameter("username", username);
        User result = query.getSingleResult();
        return result;
    }

    @Override
    public User findById(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        User theUser = currentSession.get(User.class, id);
        return theUser;
    }

    @Override
    public List<User> findByRole(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createNamedQuery("findByRole", User.class);
        query.setParameter("role", id);
        List<User> result = query.getResultList();
        return result;
    }
}
