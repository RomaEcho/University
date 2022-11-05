package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void editComment(Comment comment);

    void removeComment(Comment comment);

    Comment getComment(Long id);

    List<Comment> getAllComments();

}