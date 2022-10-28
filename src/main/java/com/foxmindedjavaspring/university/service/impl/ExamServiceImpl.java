package com.foxmindedjavaspring.university.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.foxmindedjavaspring.university.dao.ExamDao;
import com.foxmindedjavaspring.university.model.Exam;
import com.foxmindedjavaspring.university.service.ExamService;

@Component
public class ExamServiceImpl implements ExamService {
    private final ExamDao examDao;

    public ExamServiceImpl(ExamDao examDao) {
        this.examDao = examDao;
    }

    @Override
    public void addExam(Exam exam) {
        examDao.create(exam);
    }

    @Override
    public void removeExam(Exam exam) {
        examDao.delete(exam);
    }

    @Override
    public Exam getExam(Long id) {
        return examDao.findById(id);
    }

    @Override
    public List<Exam> getAllExams() {
        return examDao.findAll();
    }

    @Override
    public void editExam(Exam exam) {
        examDao.update(exam);
    }

}
