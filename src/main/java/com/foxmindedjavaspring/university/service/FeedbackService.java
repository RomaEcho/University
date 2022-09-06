package com.foxmindedjavaspring.university.service;

import java.util.List;

import com.foxmindedjavaspring.university.model.Feedback;

public interface FeedbackService {

    void addFeedback(Feedback feedback);

    void removeFeedback(long id);

    void editFeedback(Integer rating, long feedbackId);

    Feedback getFeedback(long id);

    List<Feedback> getAllFeedbacks();

}
