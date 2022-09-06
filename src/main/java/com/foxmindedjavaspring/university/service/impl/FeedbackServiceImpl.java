package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.FeedbackDao;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.service.FeedbackService;

@Component
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackDao feedbackDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public void addFeedback(Feedback feedback) {
        feedbackDao.create(feedback);
    }

    @Override
    public void removeFeedback(Long id) {
        feedbackDao.delete(id);
    }

    @Override
    public Feedback getFeedback(Long id) {
        return feedbackDao.findById(id);
    }

    @Override
    public void editFeedback(Integer rating, Long feedbackId) {
        feedbackDao.update(rating, feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackDao.findAll();
    }
}
