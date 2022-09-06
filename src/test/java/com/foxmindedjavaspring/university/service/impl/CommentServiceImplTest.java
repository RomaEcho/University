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
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentServiceImpl.addComment(text, feedbackId);

        verify(commentDao).create(text, feedbackId);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentServiceImpl.removeComment(id);

        verify(commentDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingComment() {
        commentServiceImpl.getComment(id);

        verify(commentDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllComments() {
        commentServiceImpl.getAllComments();

        verify(commentDao).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingComment() {
        commentServiceImpl.editComment(text, feedbackId);

        verify(commentDao).update(text, feedbackId);
    }
}
