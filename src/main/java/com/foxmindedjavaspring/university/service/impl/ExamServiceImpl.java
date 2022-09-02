package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.service.ExamService;

@Component
public class ExamServiceImpl implements ExamService {
    private final GenericDao genericDao;

    public ExamServiceImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addExam(Exam exam) {
        genericDao.create(exam);
    }

    @Override
    public void removeExam(Exam exam) {
        genericDao.delete(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return genericDao.findAll();
    }

}
