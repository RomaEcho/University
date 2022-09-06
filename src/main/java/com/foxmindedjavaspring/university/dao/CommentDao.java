package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentDao {

    int create(String text, Long feedbackId);

    int update(String text, Long feedbackId);

    int delete(Long id);

    Comment findById(Long id);

    List<Comment> findAll();

}
