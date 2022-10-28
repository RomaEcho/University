package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Feedback;

@Repository
public class FeedbackDao extends AbstractGenericDao<Feedback> {
    public FeedbackDao() {
        super(Feedback.class);
    }
}
