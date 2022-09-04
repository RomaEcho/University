package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.service.CommentService;

@Component
public class CommentServiceImpl implements CommentService {
    private final CommentDao commentDao;

    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void addComment(Feedback feedback) {
        commentDao.create(feedback);
    }

    @Override
    public void removeComment(Feedback feedback) {
        commentDao.delete(feedback);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.findAll();
    }

    @Override
    public void editComment(Feedback feedback) {
        commentDao.update(feedback);
    }
}
