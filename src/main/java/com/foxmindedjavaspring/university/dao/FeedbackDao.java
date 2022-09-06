package com.foxmindedjavaspring.university.dao;

import com.foxmindedjavaspring.university.model.Feedback;

public interface FeedbackDao extends GenericDao<Feedback> {
    int update(Integer rating, Long id);
}
