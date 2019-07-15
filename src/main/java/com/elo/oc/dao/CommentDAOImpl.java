package com.elo.oc.dao;

import com.elo.oc.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Comment> getComments() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
        Root<Comment> root = cq.from(Comment.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void saveComment(Comment comment) {
        Session currentSession = sessionFactory.getCurrentSession();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        comment.setDate(dateFormat.format(now));
        currentSession.saveOrUpdate(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Comment theComment = session.byId(Comment.class).load(id);
        session.delete(theComment);
    }

    @Override
    public Comment findCommentById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Comment theComment = currentSession.get(Comment.class, id);
        return theComment;
    }

}
