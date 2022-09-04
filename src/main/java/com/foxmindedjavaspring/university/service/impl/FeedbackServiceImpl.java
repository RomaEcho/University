package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Feedback;
import com.foxmindedjavaspring.university.service.FeedbackService;

@Component
public class FeedbackServiceImpl implements FeedbackService {
    private final GenericDao genericDao;

    public FeedbackServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addFeedback(Feedback feedback) {
        genericDao.create(feedback);
    }

    @Override
    public void removeFeedback(Feedback feedback) {
        genericDao.delete(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return genericDao.findAll();
    }
}
