package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(String text, Long feedbackId) {
        commentDao.create(text, feedbackId);
    }

    @Override
    public void removeComment(Long id) {
        commentDao.delete(id);
    }

    @Override
    public Comment getComment(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    @Override
    public void editComment(String text, Long feedbackId) {
        commentDao.update(text, feedbackId);
    }
}
