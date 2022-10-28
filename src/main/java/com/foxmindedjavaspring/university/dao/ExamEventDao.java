package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.ExamEvent;

@Repository
public class ExamEventDao extends AbstractGenericDao<ExamEvent> {
    public ExamEventDao() {
        super(ExamEvent.class);
    }
}
