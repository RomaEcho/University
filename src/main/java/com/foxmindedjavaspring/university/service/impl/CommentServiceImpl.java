package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.CommentRepository;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.service.CommentService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    static final String GET_COMMENT_ERROR = "::Error while getting comment with id";
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_COMMENT_ERROR, id)));
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public void editComment(Comment comment) {
        commentRepository.save(comment);
    }
}
