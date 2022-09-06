package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.GenericDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.service.ExamService;

@Component
public class ExamServiceImpl implements ExamService {
    private final GenericDao<Exam> genericDao;

    public ExamServiceImpl(GenericDao<Exam> genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public void addExam(Exam exam) {
        genericDao.create(exam);
    }

    @Override
    public void removeExam(Long id) {
        genericDao.delete(id);
    }

    @Override
    public Exam getExam(Long id) {
        return genericDao.findById(id);
    }

    @Override
    public List<Exam> getAllExams() {
        return genericDao.findAll();
    }

}
