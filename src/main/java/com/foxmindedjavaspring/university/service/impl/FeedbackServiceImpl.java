package com.foxmindedjavaspring.university.service.impl;

import com.foxmindedjavaspring.university.repository.FeedbackRepository;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.service.FeedbackService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackServiceImpl implements FeedbackService {
    static final String GET_FEEDBACK_ERROR = "::Error while getting feedback with id";
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void addFeedback(@NonNull Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void removeFeedback(@NonNull Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    @Override
    public Feedback getFeedback(Long id) {
        return feedbackRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("%s: %s",
                        GET_FEEDBACK_ERROR, id)));
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void editFeedback(@NonNull Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
