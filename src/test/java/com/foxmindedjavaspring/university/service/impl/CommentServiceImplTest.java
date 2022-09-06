package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.CommentDao;

public class CommentServiceImplTest {
    private static final long id = 11;
    private static final long feedbackId = 1;
    private static final String text = "text";
    @Mock
    private CommentDao commentDao;
    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentService.addComment(text, feedbackId);

        verify(commentDao).create(text, feedbackId);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentService.removeComment(id);

        verify(commentDao).delete(id);
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
        commentService.editComment(text, feedbackId);

        verify(commentDao).update(text, feedbackId);
    }
}
