package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.CommentDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Student;

public class CommentServiceImplTest {
    private Feedback feedback;
    @Mock
    private CommentDao commentDao;
    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        feedback = new Feedback.Builder()
                               .withRating(3)
                               .withUpdateDate(LocalDateTime.now())
                               .withUpdateDate(LocalDateTime.now())
                               .withComment(new Comment.Builder()
                                            .withText("text")
                                            .build())
                               .withStudent(new Student.Builder<>()
                                            .withStaffId((long) 333)
                                            .build())
                               .build();

    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewComment() {
        commentServiceImpl.addComment(feedback);

        verify(commentDao).create(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingComment() {
        commentServiceImpl.removeComment(feedback);

        verify(commentDao).delete(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllComments() {
        commentServiceImpl.getAllComments();

        verify(commentDao).findAll();
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingComment() {
        commentServiceImpl.editComment(feedback);

        verify(commentDao).update(feedback);
    }
}
