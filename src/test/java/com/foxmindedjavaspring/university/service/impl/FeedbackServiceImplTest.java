package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.FeedbackDao;
import com.foxmindedjavaspring.university.model.Comment;
import com.foxmindedjavaspring.university.model.Course;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.model.Student;

public class FeedbackServiceImplTest {
    private static final long id = 11;
    private Feedback feedback;
    @Mock
    private FeedbackDao feedbackDao;
    @InjectMocks
    private FeedbackServiceImpl feedbackService;

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
        feedbackService.addFeedback(feedback);

        verify(feedbackDao).create(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFeedback() {
        feedbackService.removeFeedback(id);

        verify(feedbackDao).delete(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingFeedback() {
        feedbackService.getFeedback(id);

        verify(feedbackDao).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFaculties() {
        feedbackService.getAllFeedbacks();

        verify(feedbackDao).findAll();
    }
}
