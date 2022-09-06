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
    public void addComment(String text, long feedbackId) {
        commentDao.create(text, feedbackId);
    }

    @Override
    public void removeComment(long id) {
        commentDao.delete(id);
    }

    @Override
    public Comment getComment(long id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    @Override
    public void editComment(String text, long feedbackId) {
        commentDao.update(text, feedbackId);
    }
}
