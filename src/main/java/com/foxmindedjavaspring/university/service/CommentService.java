package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentService {

    void addComment(Comment comment);

    void editComment(Comment comment);

    void removeComment(Comment comment);

    Comment getComment(Long id);

    List<Comment> getAllComments();

}