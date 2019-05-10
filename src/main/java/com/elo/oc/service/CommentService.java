package com.elo.oc.service;

import com.elo.oc.entity.Comment;

import java.util.List;

public interface  CommentService {

    List <Comment> getComments();

    void saveComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Integer id);

    Comment findCommentById(Integer id);
}