package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;

public class CommentServiceImplTest {
    private static final long id = 11;
    private Comment comment;
    @Mock
    private CommentDao commentDao;
    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        comment = new Comment();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentService.addComment(comment);

        verify(commentDao).create(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentService.removeComment(comment);

        verify(commentDao).delete(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingComment() {
        commentService.getComment(id);

        verify(commentDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllComments() {
        commentService.getAllComments();

        verify(commentDao).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingComment() {
        commentService.editComment(comment);

        verify(commentDao).update(comment);
    }
}
