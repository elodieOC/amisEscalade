package com.elo.oc.dao;

import com.elo.oc.entity.NewsletterSuscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class NewsletterSuscriberDAOImpl implements NewletterSuscriberDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveNewsletterSuscriber(NewsletterSuscriber suscriber) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(suscriber);
    }

    @Override
    public void deleteNewsletterSuscriber(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(id);
    }

    @Override
    public List<NewsletterSuscriber> getNewsletterSuscribers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< NewsletterSuscriber > cq = cb.createQuery(NewsletterSuscriber.class);
        Root< NewsletterSuscriber > root = cq.from(NewsletterSuscriber.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public NewsletterSuscriber findNewsletterSuscriberById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        NewsletterSuscriber suscriber = currentSession.get(NewsletterSuscriber.class, id);
        return suscriber;
    }


}
