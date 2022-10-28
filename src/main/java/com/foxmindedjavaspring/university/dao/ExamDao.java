package com.foxmindedjavaspring.university.dao;

import org.springframework.stereotype.Repository;

import com.foxmindedjavaspring.university.model.Exam;

@Repository
public class ExamDao extends AbstractGenericDao<Exam> {
    public ExamDao() {
        super(Exam.class);
    }
}
