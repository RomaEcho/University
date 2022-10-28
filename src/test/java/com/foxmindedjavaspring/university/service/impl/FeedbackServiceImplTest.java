package com.foxmindedjavaspring.university.service.impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.dao.FeedbackDao;
import com.foxmindedjavaspring.university.model.Feedback;

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
        feedback = new Feedback();
    }

    @Test
    void shouldVerifyAllInvocationsWhileAddingNewFeedback() {
        feedbackService.addFeedback(feedback);

        verify(feedbackDao).create(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFeedback() {
        feedbackService.removeFeedback(feedback);

        verify(feedbackDao).delete(feedback);
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
