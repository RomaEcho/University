package com.foxmindedjavaspring.university.service;

import com.foxmindedjavaspring.university.model.Feedback;

import java.util.List;

public interface FeedbackService {

    void addFeedback(Feedback feedback);

    void removeFeedback(Feedback feedback);

    void editFeedback(Feedback feedback);

    Feedback getFeedback(Long id);

    List<Feedback> getAllFeedbacks();
}
