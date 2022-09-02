package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentService {

    void addComment(Comment comment);

    void removeComment(Comment comment);

    List<Comment> getAllComments();

    void editComment(Comment comment);

    void editRating(Comment comment);

}