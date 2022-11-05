package com.foxmindedjavaspring.university.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.foxmindedjavaspring.university.repository.FeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxmindedjavaspring.university.model.Feedback;

import java.util.Optional;

public class FeedbackServiceImplTest {
    private static final long id = 11;
    private Feedback feedback;
    @Mock
    private FeedbackRepository feedbackRepository;
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

        verify(feedbackRepository).save(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileUpdatingFeedback() {
        feedbackService.editFeedback(feedback);

        verify(feedbackRepository).save(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileRemovingFeedback() {
        feedbackService.removeFeedback(feedback);

        verify(feedbackRepository).delete(feedback);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingFeedback() {
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.of(feedback));

        feedbackService.getFeedback(id);

        verify(feedbackRepository).findById(id);
    }

    @Test
    void shouldVerifyAllInvocationsWhileGettingAllFeedbacks() {
        feedbackService.getAllFeedbacks();

        verify(feedbackRepository).findAll();
    }

    @Test
    void shouldVerifyExceptionThrowWhileGettingFeedback() {
        when(feedbackRepository.findById(anyLong())).thenReturn(Optional.empty());
        String expectedMessage = String.format("%s: %s",
                FeedbackServiceImpl.GET_FEEDBACK_ERROR, id);

        Exception exception = assertThrows(IllegalStateException.class,
                () -> feedbackService.getFeedback(id));
        String actualMessage = exception.getMessage();

        verify(feedbackRepository).findById(id);
        assertEquals(expectedMessage, actualMessage);
    }
}
