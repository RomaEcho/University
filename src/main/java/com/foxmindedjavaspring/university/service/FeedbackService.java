package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Feedback;

public interface FeedbackService {

    void addFeedback(Feedback feedback);

    void removeFeedback(Long id);

    void editFeedback(Integer rating, Long feedbackId);

    Feedback getFeedback(Long id);

    List<Feedback> getAllFeedbacks();

}
