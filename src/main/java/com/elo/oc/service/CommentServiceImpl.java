package com.elo.oc.service;

import com.elo.oc.dao.CommentDAO;
import com.elo.oc.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;
    
    @Override
    public List<Comment> getComments() {
        return commentDAO.getComments();
    }

    @Override
    public void saveComment(Comment comment) {
        commentDAO.saveComment(comment);
    }
    @Override
    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentDAO.deleteComment(id);
    }

    @Override
    public Comment findCommentById(Integer id) {
        return commentDAO.findCommentById(id);
    }
}
