package com.foxmindedjavaspring.university.dao;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;

public interface CommentDao {
    int create(Feedback feedback);

    int delete(long id);

    int delete(Feedback feedback);

    Comment findById(long id);

    List<Comment> findAll();
    
    int update(Feedback feedback);

}
