package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Student;

public class FeedbackServiceImplTest {
    private Feedback feedback;
    @Mock
    private GenericDao genericDao;
    @InjectMocks
    private FeedbackServiceImpl feedbackServiceImpl;

    @BeforeEach
    void setUp() {
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
                               .withCourse(new Course.Builder()
                                            .withTopic("topic")
                                            .build())
                               .build();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewFeedback() {
        feedbackServiceImpl.addFeedback(feedback);

        verify(genericDao).create(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFeedback() {
        feedbackServiceImpl.removeFeedback(feedback);

        verify(genericDao).delete(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        feedbackServiceImpl.getAllFeedbacks();

        verify(genericDao).findAll();
    }
}
