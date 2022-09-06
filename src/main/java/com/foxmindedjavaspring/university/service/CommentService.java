package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentService {

    void addComment(String text, long feedbackId);

    void editComment(String text, long feedbackId);

    void removeComment(long id);

    Comment getComment(long id);

    List<Comment> getAllComments();

}