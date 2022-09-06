package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentDao {

    int create(String text, long feedbackId);

    int update(String text, long feedbackId);

    int delete(long id);

    Comment findById(long id);

    List<Comment> findAll();

}
