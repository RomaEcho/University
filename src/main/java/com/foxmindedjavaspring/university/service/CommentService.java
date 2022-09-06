package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Comment;

public interface CommentService {

    void addComment(String text, Long feedbackId);

    void editComment(String text, Long feedbackId);

    void removeComment(Long id);

    Comment getComment(Long id);

    List<Comment> getAllComments();

}