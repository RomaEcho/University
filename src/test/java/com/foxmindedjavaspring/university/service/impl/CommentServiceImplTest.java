package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Student;

public class CommentServiceImplTest {
    private Comment comment;
    @Mock
    private CommentDao commentDao;
    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        comment = new Comment.Builder()
                             .withStudent(new Student.Builder<>()
                                                     .withStaffId((long) 111)
                                                     .build())      
                             .withComment("comment")
                             .withRating(5)
                             .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentServiceImpl.add(comment);

        verify(commentDao).create(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentServiceImpl.remove(comment);

        verify(commentDao).delete(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllComments() {
        commentServiceImpl.getAll();

        verify(commentDao).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingComment() {
        commentServiceImpl.editComment(comment);

        verify(commentDao).updateComment(comment);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingRating() {
        commentServiceImpl.editRating(comment);

        verify(commentDao).updateRating(comment);
    }
}
