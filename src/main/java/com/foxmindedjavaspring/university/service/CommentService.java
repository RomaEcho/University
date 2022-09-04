package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;

public interface CommentService {

    void addComment(Feedback feedback);

    void removeComment(Feedback feedback);

    List<Comment> getAllComments();

    void editComment(Feedback feedback);

}