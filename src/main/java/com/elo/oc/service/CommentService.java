package com.elo.oc.service;

import com.elo.oc.entity.Comment;

import java.util.List;

public interface  CommentService {

    List <Comment> getComments();

    void saveComment(Comment comment);
    void deleteComment(Integer id);

    Comment findById(Integer id);
}