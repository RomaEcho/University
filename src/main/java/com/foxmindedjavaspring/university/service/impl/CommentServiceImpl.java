package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService<Comment> {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void add(Comment comment) {
        commentDao.create(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public void editComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void editRating(Comment comment) {
        commentDao.updateRating(comment);
    }
}
